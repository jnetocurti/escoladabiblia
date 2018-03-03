
$ cd src/site/env/dev/docker-db

$ docker build -t escoladabiblia .

$ docker run --name escoladabiblia -d -p 5432:5432 escoladabiblia
