------------------------------------------------------------------------------------------
$ cd C:/Users/Neto/Desenvolvimento/PROJETOS/EscolaDaBiblia/escoladabiblia/src/site/docker

$ docker build -t escoladabiblia .

$ docker run --name escoladabiblia -d escoladabiblia

$ docker run -it --rm --link escoladabiblia:escoladabiblia escoladabiblia psql -h escoladabiblia -U escoladabiblia

------------------------------------------------------------------------------------------