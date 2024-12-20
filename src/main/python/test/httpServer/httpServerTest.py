from fastapi.testclient import TestClient
from httpServer.httpServer import app

client = TestClient(app)

def test_bonjour_handler():
    response = client.get("/bonjour")
    assert response.status_code == 200
    assert response.json() == {"bericht": "Bonjour from Python"}
