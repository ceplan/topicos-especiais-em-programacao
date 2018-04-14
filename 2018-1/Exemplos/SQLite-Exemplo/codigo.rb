require 'sqlite3'

# Cria banco de dados
db = SQLite3::Database.new 'exemplo02.db'

# Criar tabela
result = db.execute <<-SQL
  CREATE TABLE agenda (
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR(80),
    telefone VARCHAR(25),
    idade INTEGER
  );
SQL

# Executar um INSERT
db.execute "INSERT INTO agenda VALUES (1,'Luís', '4790002000', 24),
                                      (2,'Rodrigo', '4795002000', 25),
                                      (3,'Marco','4795002000', 25)"

# Executar um SELECT
db.execute 'SELECT * FROM agenda' do |row|
  # Exibir a linha no console
  puts row
end
