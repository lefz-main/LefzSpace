from fastapi.testclient import TestClient
from httpServer import app

client = TestClient(app)
#tuurlijk werkt het niet. fkcing eerste keer test schrijven in python SOS HELP MIJ!!!
def test_bonjour_handler():
    response = client.get("/bonjour")
    assert response.status_code == 200
    assert response.json() == {"bericht": "Bonjour from Python"}