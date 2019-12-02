# io.klib.docker.builder
Using a docker container to build a project via mounting needed directories


## Used command

docker run -it
    --name devtest
    --mount type=bind,source="$(pwd)"/project,target=/project,readonly
    --mount type=bind,source="$(pwd)"/result,target=/result
    adoptopenjdk/openjdk11:slim
    bash -c "cd ./project && ./gradlew export.launch && /bin/bash"


## Explanations

-it : interactive, attaches stdout to the container
--name : assigns container a specific name
--mount : Mount source to target,roadonly (for read/write skip readonly)
adoptopenjdk/openjdk11:slim is the used image
bash -c : gives the string as command to the container
