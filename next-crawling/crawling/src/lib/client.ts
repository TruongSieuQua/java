import { MongoClient } from 'mongodb';

const uri = 'mongodb://root:root@localhost:27017/';
const client = new MongoClient(uri);

let db;
let wordsCollection;

async function connectToDatabase() {
    await client.connect();
}

async function closeDatabaseConnection() {
  try {
    await client.close();
    console.log('Closed MongoDB connection');
  } catch (error) {
    console.error('Failed to close MongoDB connection:', error);
  }
}

export { client, connectToDatabase, closeDatabaseConnection };