from kafka import KafkaProducer
import json
import time
from constants import SERVIDORES_BOOTSTRAP,TOPIC_PRODUCER_RESULTADO

def producer(mensaje,topic):
    productor = KafkaProducer(
        value_serializer=lambda m: json.dumps(m).encode('utf-8'),
        bootstrap_servers=SERVIDORES_BOOTSTRAP
    )
    time.sleep(1)
    productor.send(topic,value=mensaje)
    if(topic == TOPIC_PRODUCER_RESULTADO):
        print("\n[*] Mensaje enviado a MODULO DE PROCESAMIENTO DE ORDENES")
    else:
        print("\n[*] Mensaje enviado al MODULO DE FACTURACION")