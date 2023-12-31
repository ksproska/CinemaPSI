create-all-backend-images:
	docker build -t cinema-backend-client-service ./client-service/backend
	docker build -t cinema-backend-define-local ./define-local/backend
	docker build -t cinema-backend-repertoire ./repertoire/backend
	docker build -t cinema-backend-ticket-collector ./ticket-collector/backend
