FROM node:20.10.0 AS build
WORKDIR /usr/src/app
COPY define-local/package.json define-local/package-lock.json ./
RUN npm install --force
COPY define-local ./
RUN npm run build

FROM nginx:1.17.1-alpine AS frontend
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/define-local/browser /usr/share/nginx/html
