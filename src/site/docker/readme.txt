------------------------------------------------------------------------------------------
$ cd C:/Users/Neto/Desenvolvimento/PROJETOS/EscolaDaBiblia/escoladabiblia/src/site/docker

$ docker build -t escoladabiblia .

$ docker run --name escoladabiblia -p 5432:5432 -d escoladabiblia

$ docker run -it --rm --link escoladabiblia:escoladabiblia escoladabiblia psql -h escoladabiblia -U escoladabiblia

------------------------------------------------------------------------------------------