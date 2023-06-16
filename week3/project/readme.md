# Gestione Incendi

Il sistema distribuito permette, tramite il Centro di Controllo, di notificare eventuali allarmi quando il livello di fumo supera 5.

## fire-sensor-service

Questo microservizio si occupa di misurare i livelli di fumo e inviarli al processo di controllo competente.

Il sistema è progettato per gestire un numero arbitrario di sensori.

Ecco un esempio di file di configurazione:

server.port=8082

sensor.id=1002

sensor.latitude=41.8919300

sensor.longitude=12.5113300


Per simulare una rilevazione, è sufficiente effettuare una chiamata POST.

Esempio di chiamata:

Endpoint: POST http://localhost:8082/sensors/1002

Body:
{
    "id": 1002,
    "latitude": 20.00,
    "longitude": 40.00,
    "smokeLevel": 1
}

## control-process-service

Questo microservizio si occupa di leggere tutte le rilevazioni effettuate dai sensori di sua competenza e inviare eventuali allarmi al centro di controllo.

Il sistema è progettato per gestire un numero arbitrario di processi di controllo.

Esempio di chiamata per ottenere tutte le rilevazioni di competenza:

Endpoint: GET http://localhost:8082/sensors

Nota: È possibile aggiungere o rimuovere una sonda senza creare problemi.

## control-center-service

Questo microservizio si occupa di notificare gli allarmi al personale.

Per semplificare la simulazione, le rilevazioni che hanno generato l'allarme vengono stampate sul terminale.

Per visualizzare tutti gli allarmi, fare una chiamata GET all'endpoint:

Endpoint: GET http://localhost:8080/control-center/alarms
