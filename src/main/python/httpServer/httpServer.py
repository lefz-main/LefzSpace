from fastapi import FastAPI, Query
from pydantic import BaseModel

app = FastAPI()

class ResponseModel(BaseModel):
    bericht: str

@app.get("/test", response_model=ResponseModel)
async def test_handler():
    return {"bericht": "Bonjour from Python"}

@app.get("/tryLogin", response_model=ResponseModel)
async def try_login(mail: str = Query(...), password: str = Query(...)):
    if mail != "test1":
        return {"bericht": "Mail not Found"}
    if password != "123":
        return {"bericht": "Wrong Password!"}
    return {"bericht": "Logged In!"}

@app.get("/getMail", response_model=ResponseModel)
async def getMail_handler():
    return {"bericht": "Mail Json"}

@app.get("/GoOffline", response_model=ResponseModel)
async def offline_handler():
    return {"bericht": "Informatie wordt opgehaald voor Offline modus"}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8089) #TODO Host -> domein intergratie

#wajo hierin is het makelijker dan golong
