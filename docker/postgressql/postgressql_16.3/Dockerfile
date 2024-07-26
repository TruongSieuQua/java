ARG alpineVersion=3.20.2

FROM alpine:${alpineVersion}

ARG postgresVersion=16.3-r0

RUN apk update && apk add --no-cache \
    postgresql16=${postgresVersion} \
    postgresql16-contrib=${postgresVersion} \
    shadow \
    su-exec

ENV PGDATA /var/lib/postgresql/data

# lockfile path
RUN mkdir -p /run/postgresql && \
    chown postgres:postgres /run/postgresql

RUN mkdir -p ${PGDATA} && \
    chown -R postgres:postgres ${PGDATA} && \
    su-exec postgres initdb -D ${PGDATA}

COPY entrypoint.sh /usr/local/bin/

RUN chmod +x /usr/local/bin/entrypoint.sh

USER postgres

EXPOSE 5432

ENTRYPOINT ["entrypoint.sh"]

#Build command: docker build -t tjn/postgres:16.3 .