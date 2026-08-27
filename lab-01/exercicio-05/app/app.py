import redis
from flask import Flask 

# objeto da classe Flask; __name__ é convencao
app = Flask(__name__)

# objeto da classe Redis. Conecterá no host 'redis' que está na porta 6379
db = redis.Redis(host='redis',port=6379)

# funcao
# vai conectar no banco, incrementa a chave e retorna o valor dela
def incrementa_valor():
    try:
        return db.incr('contador')
    except redis.exceptions.ConnectiorError:
        return -1

# quando alguem acessar a minha maquina via HTTP, e acessar a '/', 
# ele invocará o inicial()
@app.route('/')
def inicial():
    contador = incrementa_valor()
    return f'Você é o visitante número: {contador}\n'