Para usar este proyecto primero hay que clonar el repositorio con git clone https://github.com/johnny567w/SegarraJ_Evaluacion,
luego entramos a la carpeta que se descargó y nos movemos a la carpeta Back con cd back y estaremos en la raiz del proyecto.
Desde ahí levantamos los servicios con docker-compose up -d solo para ver que funcione de manera local, 
Despues iniciamos minikube con minikube start y una vez iniciado aplicamos los archivos yaml con los comandos kubectl apply -f postgres-deployment.yaml y kubectl apply -f universidades-api-deployment.yaml
eso va a desplegar tanto la base de datos como el backend con 3 réplicas, para ver las replicas se puede usar minikube dashaboar
despues copiamos el nombre de uno de los pods que esté en estado Running y luego hacemos el port-forward con kubectl port-forward (nombre del pod que ya copiamos)8080:8080
ya con eso abrimos el navegador y entramos a http://localhost:8080/api/universidades y se vere el contenido cargado de init sql
