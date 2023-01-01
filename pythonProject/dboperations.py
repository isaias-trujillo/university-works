import conecction as conexion
import producer
from constants import TOPIC_PRODUCER_RESULTADO

def verificarStock(lista):
    estado = True
    con = conexion.connection()
    if(con):
        try:
            with con.cursor() as cursor:
                for item in lista:
                    query = "SELECT stock FROM articulos WHERE id_articulo=%s;"
                    cursor.execute(query,(item['codigo']))
                    articulo_stock = cursor.fetchall()
                    if(articulo_stock[0][0] < item['cantidad']):
                        estado = False
                        confirmacion = {"codigo": 1, "mensaje": "Stock no Disponible del producto " + item["nombre"]}
                        producer.producer(confirmacion,TOPIC_PRODUCER_RESULTADO)

        finally:
            con.close()
    else:
        print("[*] Internal db server error")
    return estado

def realizarReserva(lista):
    con = conexion.connection()
    if(con):
        try:
            with con.cursor() as cursor:
                for item in lista:
                    query = "UPDATE articulos SET stock = stock - %s WHERE id_articulo = %s;"
                    cursor.execute(query,(item['cantidad'],item['codigo']))
            con.commit()
            print("[*] Se ha realizado la reserva de los articulos de manera existosa!!")
        finally:
            con.close()
