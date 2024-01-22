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
