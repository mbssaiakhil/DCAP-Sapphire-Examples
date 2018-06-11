#!/bin/bash

build_app() {
    cd ../
    gradle build
    cp build/libs/TodoConsole.jar deploy/
    cd deploy
    sudo docker build -t saiakhil2012/todoconsole:sapphirized .
}

deploy_OMS() {
    sudo docker network create --subnet=172.18.0.0/16 dcap_net
    sudo docker run -i -d --name omsserver --net dcap_net --ip 172.18.0.2 saiakhil2012/sapphire-core:latest
    sudo docker cp libs/sapphire-core.jar omsserver:/sapphire-core.jar
    sudo docker cp TodoConsole.jar omsserver:/app.jar
    if [ "$1" = "d" ]
    then
        sudo docker exec -it -d omsserver java -cp sapphire-core.jar:java.rmi.jar:app.jar:apache.harmony.jar sapphire.oms.OMSServerImpl 172.18.0.2 22346 cloud.TodoConsoleStart
    else
        sudo docker exec -it omsserver java -cp sapphire-core.jar:java.rmi.jar:app.jar:apache.harmony.jar sapphire.oms.OMSServerImpl 172.18.0.2 22346 cloud.TodoConsoleStart
    fi
}

deploy_KS1() {
    sudo docker run -i -d --name kernelserver1 --net dcap_net --ip 172.18.0.3 saiakhil2012/sapphire-core:latest
    sudo docker cp libs/sapphire-core.jar kernelserver1:/sapphire-core.jar
    sudo docker cp TodoConsole.jar kernelserver1:/app.jar
    if [ "$1" = "d" ]
    then
        sudo docker exec -it -d kernelserver1 java -Djava.security.manager -Djava.rmiserver.useCodebaseOnly=false -Djava.security.policy=./client.policy -cp sapphire-core.jar:java.rmi.jar:app.jar:apache.harmony.jar sapphire.kernel.server.KernelServerImpl 172.18.0.3 22343 172.18.0.2 22346
    else
        sudo docker exec -it kernelserver1 java -Djava.security.manager -Djava.rmiserver.useCodebaseOnly=false -Djava.security.policy=./client.policy -cp sapphire-core.jar:java.rmi.jar:app.jar:apache.harmony.jar sapphire.kernel.server.KernelServerImpl 172.18.0.3 22343 172.18.0.2 22346
    fi
}

deploy_KS2() {
    sudo docker run -i -d --name kernelserver2 --net dcap_net --ip 172.18.0.4 saiakhil2012/sapphire-core:latest
    sudo docker cp libs/sapphire-core.jar kernelserver2:/sapphire-core.jar
    sudo docker cp TodoConsole.jar kernelserver2:/app.jar
    if [ "$1" = "d" ]
    then
        sudo docker exec -it -d kernelserver2 java -Djava.security.manager -Djava.rmiserver.useCodebaseOnly=false -Djava.security.policy=./client.policy -cp sapphire-core.jar:java.rmi.jar:app.jar:apache.harmony.jar sapphire.kernel.server.KernelServerImpl 172.18.0.4 22344 172.18.0.2 22346
    else
        sudo docker exec -it kernelserver2 java -Djava.security.manager -Djava.rmiserver.useCodebaseOnly=false -Djava.security.policy=./client.policy -cp sapphire-core.jar:java.rmi.jar:app.jar:apache.harmony.jar sapphire.kernel.server.KernelServerImpl 172.18.0.4 22344 172.18.0.2 22346
    fi
}

deploy_app() {
    sudo docker run -i -d --name app --net dcap_net --ip 172.18.0.5 saiakhil2012/todoconsole:sapphirized
    sudo docker cp libs/sapphire-core.jar kernelserver2:/sapphire-core.jar
    sudo docker cp TodoConsole.jar omsserver:/app.jar
    sudo docker exec -it app java -cp TodoConsole.jar:libs/* console.Console 172.18.0.2 22346 172.18.0.5 22345
}

deploy_all() {
    build_app d
    deploy_OMS d
    deploy_KS1 d
    deploy_KS2 d
    deploy_app d
}

case "$1" in
    build_app) build_app ;;

    # Deploy OMS Server
    deploy_OMS) deploy_OMS ;;

    # Deploy Kernel Server 1
    deploy_KS1) deploy_KS1 ;;

    # Deploy Kernel Server 2
    deploy_KS2) deploy_KS2 ;;

    # Deploy User Application
    deploy_app) deploy_app ;;

    # Deploy all one after the other in daemon mode, and then start User Application
    deploy_all) deploy_all ;;
esac