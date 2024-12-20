from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class ResponseModel(BaseModel):
    bericht: str

@app.get("/bonjour", response_model=ResponseModel)
async def bonjour_handler():
    return {"bericht": "Bonjour from Python"}

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8080)

#wajo hierin is het makelijker dan golong
