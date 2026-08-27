FROM python:3.14-slim

WORKDIR /app

RUN pip install flask redis
# redis para conectar ao banco de dados (chave + valor)
# flask: mini servidor WEB (é do PYTHON)

# Variaveis de ambiente
ENV FLASK_APP=app.py 
ENV FLASK_RUN_HOST=0.0.0.0
ENV PYTHONUNBUFFERED=1

# Porta interna do container
EXPOSE 5000

CMD [ "flask", "run" ]
