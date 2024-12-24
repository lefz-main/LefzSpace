from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class ResponseModel(BaseModel):
    bericht: str

@app.get("/test", response_model=ResponseModel)
async def test_handler():
    return {"bericht": "Bonjour from Python"}

@app.get("/getMail", response_model=ResponseModel)
async def getMail_handler():
    return {"bericht": "Mail Json"}

@app.get("/GoOffline", response_model=ResponseModel)
async def offline_handler():
    return {"bericht": "Informatie wordt opgehaald voor Offline modus"}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8080) #TODO Host -> domein intergratie

#wajo hierin is het makelijker dan golong
