use admin

db.createUser({
  user: 'admin1',
  pwd: '123456',
  roles: [
    {
      role: 'userAdminAnyDatabase',
      db: 'admin',
    },
  ],
});
