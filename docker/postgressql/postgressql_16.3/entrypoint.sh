#!/bin/sh
# Exit if any command encounter error
set -e

# Print the postgresql configuration file
cat /var/lib/postgresql/data/postgresql.conf

# Run postgresql
postgres -D /var/lib/postgresql/data