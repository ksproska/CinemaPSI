create-all-backend-images:
	docker build -t cinema-backend-client-service ./client-service/backend
	docker build -t cinema-backend-define-local ./define-local/backend
	docker build -t cinema-backend-repertoire ./repertoire/backend
	docker build -t cinema-backend-ticket-collector ./ticket-collector/backend

create-all-frontend-images:
	docker build -t cinema-frontend-client-service ./client-service/frontend
	docker build -t cinema-frontend-define-local ./define-local/frontend
	docker build -t cinema-frontend-repertoire ./repertoire/frontend

create-all-images: create-all-backend-images create-all-frontend-images

create-mock-payment-service-image:
	docker build -t cinema-mock-payment-service ./client-service/payment-service
	docker tag cinema-mock-payment-service:latest ksproska/cinema-mock-payment-service:latest

tag-all:
	docker tag cinema-backend-client-service:latest   ksproska/cinema-backend-client-service:latest
	docker tag cinema-backend-define-local:latest     ksproska/cinema-backend-define-local:latest
	docker tag cinema-backend-repertoire:latest       ksproska/cinema-backend-repertoire:latest
	docker tag cinema-backend-ticket-collector:latest ksproska/cinema-backend-ticket-collector:latest
	docker tag cinema-frontend-client-service:latest  ksproska/cinema-frontend-client-service:latest
	docker tag cinema-frontend-define-local:latest    ksproska/cinema-frontend-define-local:latest
	docker tag cinema-frontend-repertoire:latest      ksproska/cinema-frontend-repertoire:latest

remove-all:
	docker rmi cinema-backend-client-service:latest
	docker rmi ksproska/cinema-backend-client-service:latest
	docker rmi cinema-backend-define-local:latest
	docker rmi ksproska/cinema-backend-define-local:latest
	docker rmi cinema-backend-repertoire:latest
	docker rmi ksproska/cinema-backend-repertoire:latest
	docker rmi cinema-backend-ticket-collector:latest
	docker rmi ksproska/cinema-backend-ticket-collector:latest
	docker rmi cinema-frontend-client-service:latest
	docker rmi ksproska/cinema-frontend-client-service:latest
	docker rmi cinema-frontend-define-local:latest
	docker rmi ksproska/cinema-frontend-define-local:latest
	docker rmi cinema-frontend-repertoire:latest
	docker rmi ksproska/cinema-frontend-repertoire:latest


push-all:
	docker push ksproska/cinema-backend-client-service:latest
	docker push ksproska/cinema-backend-define-local:latest
	docker push ksproska/cinema-backend-repertoire:latest
	docker push ksproska/cinema-backend-ticket-collector:latest
	docker push ksproska/cinema-frontend-client-service:latest
	docker push ksproska/cinema-frontend-define-local:latest
	docker push ksproska/cinema-frontend-repertoire:latest

all: create-all-images tag-all push-all
