import pymysql

import constants as c


def connection():
    try:
        conexion = pymysql.connect(
            host = c.HOST_NAME,
            user = c.USER,
            password = c.PASSWORD,
            db = c.DB_ARTICULOS)
        print("\n[*] Conexion Exitosa")
        return conexion
    except(pymysql.err.OperationalError, pymysql.err.InternalError) as e:
        print("\n[*] Error al conectar a la base de datos: ", e)