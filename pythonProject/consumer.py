from kafka import KafkaConsumer
import json
from constants import TOPIC_CONSUMER,SERVIDORES_BOOTSTRAP, TOPIC_PRODUCER_RESULTADO, TOPIC_PRODUCER_FACTURACION
import dboperations
import producer

def consume():
    consumidor = KafkaConsumer(
        TOPIC_CONSUMER,
        bootstrap_servers=SERVIDORES_BOOTSTRAP,
        value_deserializer=lambda m: json.loads(m.decode('utf-8')),
    )
    for msg in consumidor:
        print("\n[*] Se ha recibido un mensaje del MODULO DE PROCESAMIENTO DE ORDENES")
        print("\n[*] Desempaquetando mensaje ... \n")
        mensaje = msg.value
        orden_lista = mensaje['lista']
        estado_stock = dboperations.verificarStock(orden_lista)
        if(estado_stock):
            dboperations.realizarReserva(orden_lista)
            producer.producer(mensaje,TOPIC_PRODUCER_FACTURACION)
            confirmacion = {"codigo": 2,
                            "mensaje": "Procede su orden"}
            producer.producer(confirmacion, TOPIC_PRODUCER_RESULTADO)
        else:
            confirmacion = {"codigo": 1, "mensaje": "No se pudo realizar la reserva de su pedido por stock insuficiente"}
            producer.producer(confirmacion,TOPIC_PRODUCER_RESULTADO)