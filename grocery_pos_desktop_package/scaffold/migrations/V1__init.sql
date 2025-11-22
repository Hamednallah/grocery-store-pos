-- V1__init.sql
PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username TEXT UNIQUE NOT NULL,
  password_hash TEXT NOT NULL,
  full_name TEXT,
  role TEXT NOT NULL,
  created_at TEXT NOT NULL DEFAULT (datetime('now')),
  updated_at TEXT
);

CREATE TABLE IF NOT EXISTS products (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  sku TEXT,
  barcode TEXT,
  name_ar TEXT NOT NULL,
  name_en TEXT,
  category TEXT,
  unit TEXT,
  purchase_price_last REAL DEFAULT 0,
  purchase_price_avg REAL DEFAULT 0,
  sell_price REAL DEFAULT 0,
  qty REAL DEFAULT 0,
  expiry_date TEXT,
  expiry_warning_offset TEXT,
  low_stock_threshold REAL DEFAULT 0,
  description TEXT,
  created_by INTEGER,
  created_at TEXT DEFAULT (datetime('now')),
  updated_at TEXT
);

-- Insert default admin user (password: admin)
INSERT OR IGNORE INTO users (username, password_hash, full_name, role)
VALUES ('admin', '$2a$10$JWi7Oq541Qe/7AuSISwepOI8Y1Kjr1w8vlwU3B1X9fUHvZVQQoDcu', 'Admin User', 'ADMIN');
