CREATE DATABASE Biblioteca;
USE Biblioteca;

CREATE TABLE Livros (
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Titulo VARCHAR (200) NOT NULL,
    Autor VARCHAR (100) NOT NULL,
    Editora VARCHAR (100),
    Genero VARCHAR (100),
    Ano YEAR,
    ISBN VARCHAR (20),
    Quantidade_Disponivel INT DEFAULT 0
);

CREATE TABLE Usuarios(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR (150) NOT NULL,
    Email VARCHAR (100),
    CPF VARCHAR (14) UNIQUE,
    Telefone VARCHAR (200)
);

CREATE TABLE Funcionarios(
	ID INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR (150) NOT NULL,
    Cargo VARCHAR (100),
    Login VARCHAR (50) UNIQUE,
    Senha VARCHAR (100)
);

CREATE TABLE Emprestimos (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_Livro INT NOT NULL,
    ID_Usuario INT NOT NULL,
    DataEmprestimo DATE NOT NULL,
    DataDevolucao DATE,
    Status VARCHAR(20) DEFAULT 'Pendente',
    FOREIGN KEY (ID_Livro) REFERENCES Livros(ID),
    FOREIGN KEY (ID_Usuario) REFERENCES Usuarios(ID)
);

CREATE TABLE Auditoria (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Acao VARCHAR(50),              
    TabelaAfetada VARCHAR(50),
    ID_Livro INT NULL,
    ID_Usuario INT NULL,
    ID_Funcionario INT NULL,
    DataHora DATETIME DEFAULT CURRENT_TIMESTAMP
);



INSERT INTO Livros (Titulo, Autor, Editora, Genero, Ano, ISBN, Quantidade_Disponivel) VALUES
('Alice No País Das Maravilhas','Lewis Carroll','Darkside','Fantasia','2019','8594541759','7'),
('Alice Através Do Espelho E O Que Ela Encontrou Por Lá','Lewis Carroll','Darkside','Fantasia','2021','6555980869','3'),
('O Mágico De Oz','L.Frank Baum','Companhia Das Letras','Literatura Infantil','2013','978-85-3781-134-4','3'),
('Viagem Ao Centro Da Terra','Jules Verne','Companhia Das Letras','Ficção Cintífica','2016','978-85-3781-551-9','5'),
('Hamlet','Willian Shakespeare','Penguin-companhia','Tragédia','2015','858285014X','8'), 
('A Divina Comédia - Inferno','Dante Alighieri','Principis','Épico','2020','6550970326','4'), 
('A Divina Comédia - Purgatório','Dante Alighieri','Principis','Épico','2020','6550970334','4'), 
('A Divina Comédia - Paraíso','Dante Alighieri','Principis','Épico','2020','6550970342','4'), 
('O Retrato De Dorian Gray','Oscar Wilde','Penguin-Companhia','Ficção Gótica','2012','8563560433','6'),
('Memórias Póstumas De Brás Cubas','Machado de Assis','Principis','Realismo,Ficção','2019','8594318618','10'), 
('O Triste Fim De Policarpo Quaresma','Lima Barreto','Penguin-Companhia','Romance','2011','8563560174','9'), 
('Capitães Da Areia','Jorge Amado','Companhia De Bolso','Romance','2009','8535914064','10'), 
('O Pequeno Príncepe','Antoine De Saint-Exupery','Novo Século','Infantojuvenil','2019','8542816064','10'), 
('Ensaio Sobre A Cegueira','José Saramago','Companhia Das Letras','Romance','2020','8535930310','8');
SELECT * FROM Livros;

SELECT * FROM Livros WHERE id = 8;

UPDATE Livros
SET quantidade_disponivel = 10
WHERE id = 3;


INSERT INTO Usuarios (Nome, Email, CPF, Telefone) VALUES
('Vanessa Witczak','wanessa.witczak2.0@gmail.com,','04291119160','66-996864259'),
('Mauricío Cavalcanti','m.cavalcanti@gmail.com','02291272940','66-999947235'),
('Lilian Monteiro','lily.monteiro@gmail.com','40093174285','66-99904208001'),
('Rosalinda Fernandes','rosafernandes@gmail.com','36985214700','66-999980240'),
('Violeta Andrade','violetandrade@gmail.com','10023167948','66-998242593'),
('Margarida Bastos','megbastos@gmail.com','08020124417','66-998771925'),
('Bruno Lobatto','bruno.lobatto@gmail.com','05512160126','66-999954235');
SELECT * FROM Usuarios;

UPDATE Usuarios
SET telefone = '(65) 98888-1111'
WHERE id = 1;


INSERT INTO Funcionarios (Nome, Cargo, Login, Senha) VALUES
('Ana Paula Mendes', 'Atendente', 'ana', '1234'),
('Carlos Roberto Silva', 'Bibliotecário', 'carlos', 'admin123'),
('Fernanda Alves', 'Gerente', 'fernanda', 'senha123'),
('João Marcos Duarte', 'Auxiliar', 'joaom', 'abcd1234'),
('Patrícia Gomes', 'Recepcionista', 'patricia', '4567');
SELECT * FROM Funcionarios;

DELETE FROM Funcionarios 
WHERE ID = 1;

INSERT INTO Funcionarios (Nome, Cargo, Login, Senha) VALUES
('Mariana Souza', 'Atendente', 'mariana', 'ms789');



INSERT INTO Emprestimos (ID_Usuario, ID_Livro, DataEmprestimo, DataDevolucao, Status) VALUES
('1','4','2025-11-03',NULL,'Atrasado'),
('2','8','2025-07-01','2025-07-07','Devolvido'),
('3','2','2025-11-14',NULL,'Atrasado'),
('4','10','2025-11-17',NULL,'Ativo'),
('5','12','2025-11-17',NULL,'Ativo'),
('6','13','2025-09-05','2025-09-12','Devolvido'),
('7','5','2025-10-30','2025-11-10','Devolvido');
SELECT * FROM Emprestimos;
ALTER TABLE Emprestimos MODIFY DataDevolucao DATE DEFAULT NULL;

UPDATE Emprestimos
SET status = 'Ativo'
WHERE id = 3;



CREATE VIEW vw_LivrosDisponiveis AS SELECT 
ID,Titulo,Autor,Editora,Genero,Ano,ISBN,Quantidade_Disponivel
FROM Livros
WHERE Quantidade_Disponivel > 0;
SELECT * FROM  vw_LivrosDisponiveis;


CREATE VIEW vw_HistoricoEmprestimos AS SELECT 
e.ID AS ID_Emprestimo,
u.Nome AS Usuario,
l.Titulo AS Livro,
e.DataEmprestimo,
e.DataDevolucao,
e.Status
FROM Emprestimos e JOIN Usuarios u ON e.ID_Usuario = u.ID
JOIN Livros l ON e.ID_Livro = l.ID;
SELECT * FROM vw_HistoricoEmprestimos;

CREATE VIEW vw_EmprestimosVencidos AS SELECT
e.ID AS ID_Emprestimo,
u.Nome AS Usuario,
l.Titulo AS Livro,
e.DataEmprestimo,
e.DataDevolucao,
e.Status
FROM Emprestimos e JOIN Usuarios u ON e.ID_Usuario = u.ID
JOIN Livros l ON e.ID_Livro = l.ID
WHERE e.DataDevolucao < CURDATE()
AND e.Status = 'Pendente';
SELECT * FROM vw_EmprestimosVencidos;
 
 
 
 
 DELIMITER //

CREATE PROCEDURE AtualizarUsuario(
    IN p_ID INT,
    IN p_Nome VARCHAR(150),
    IN p_Email VARCHAR(100),
    IN p_CPF VARCHAR(14),
    IN p_Telefone VARCHAR(200)
)
BEGIN
    UPDATE Usuarios
    SET Nome = p_Nome,
        Email = p_Email,
        CPF = p_CPF,
        Telefone = p_Telefone
    WHERE ID = p_ID;
END //

DELIMITER ;

CALL AtualizarUsuario(1, 'Vanessa Witczak', 'vanessa.w@gmail.com', '04291119160', '66996864259');



DELIMITER //

CREATE PROCEDURE AtualizarLivro(
    IN p_ID INT,
    IN p_Titulo VARCHAR(200),
    IN p_Autor VARCHAR(100),
    IN p_Editora VARCHAR(100),
    IN p_Genero VARCHAR(100),
    IN p_Ano YEAR,
    IN p_ISBN VARCHAR(20),
    IN p_Qtd INT
)
BEGIN
    UPDATE Livros
    SET Titulo = p_Titulo,
        Autor = p_Autor,
        Editora = p_Editora,
        Genero = p_Genero,
        Ano = p_Ano,
        ISBN = p_ISBN,
        Quantidade_Disponivel = p_Qtd
    WHERE ID = p_ID;
END //

DELIMITER ;
-- CALL AtualizarLivro ();--



DELIMITER //

CREATE PROCEDURE RemoverFuncionario(
    IN p_ID INT
)
BEGIN
    DELETE FROM Funcionarios
    WHERE ID = p_ID;
END //

DELIMITER ;
-- CALL RemoverFuncionario();--



DELIMITER //

CREATE PROCEDURE BuscarLivroPorTitulo(
    IN p_Titulo VARCHAR(200)
)
BEGIN
    SELECT *
    FROM Livros
    WHERE Titulo LIKE CONCAT('%', p_Titulo, '%');
END //

DELIMITER ;
-- CALL BuscarLivroPorTitulo ();--



CREATE TRIGGER trg_RegistrarEmprestimo
AFTER INSERT ON Emprestimos
FOR EACH ROW
INSERT INTO Auditoria (Acao, TabelaAfetada, ID_Livro, ID_Usuario)
VALUES ('Novo Empréstimo', 'Emprestimos', NEW.ID_Livro, NEW.ID_Usuario);


DELIMITER //

CREATE TRIGGER trg_RegistrarDevolucao
AFTER UPDATE ON Emprestimos
FOR EACH ROW
BEGIN
    IF NEW.DataDevolucao IS NOT NULL 
       AND OLD.DataDevolucao IS NULL THEN
       
        INSERT INTO Auditoria (Acao, TabelaAfetada, ID_Livro, ID_Usuario)
        VALUES ('Devolução Registrada', 'Emprestimos', NEW.ID_Livro, NEW.ID_Usuario);
        
    END IF;
END //

DELIMITER ;



DELIMITER //

CREATE TRIGGER trg_RegistrarAtualizacao
AFTER UPDATE ON Emprestimos
FOR EACH ROW
BEGIN
    -- Registro de devolução
    IF NEW.DataDevolucao IS NOT NULL AND OLD.DataDevolucao IS NULL THEN
        INSERT INTO Auditoria (Acao, TabelaAfetada, ID_Livro, ID_Usuario)
        VALUES ('Devolução Registrada', 'Emprestimos', NEW.ID_Livro, NEW.ID_Usuario);
    ELSE
        INSERT INTO Auditoria (Acao, TabelaAfetada, ID_Livro, ID_Usuario)
        VALUES ('Atualização de Empréstimo', 'Emprestimos', NEW.ID_Livro, NEW.ID_Usuario);
    END IF;
END //

DELIMITER ;



DELIMITER //

CREATE TRIGGER trg_RegistrarRemocao
AFTER DELETE ON Emprestimos
FOR EACH ROW
BEGIN
    INSERT INTO Auditoria (Acao, TabelaAfetada, ID_Livro, ID_Usuario)
    VALUES ('Empréstimo Removido', 'Emprestimos', OLD.ID_Livro, OLD.ID_Usuario);
END //

DELIMITER ;usuarios


DROP PROCEDURE IF EXISTS MenuConsultaBiblioteca;



DELIMITER //

CREATE PROCEDURE MenuConsultaBiblioteca(IN opcao INT)
BEGIN
    CASE opcao
        WHEN 1 THEN
            SELECT * FROM vw_LivrosDisponiveis;
        WHEN 2 THEN
            SELECT * FROM vw_HistoricoEmprestimos;
        WHEN 3 THEN
            SELECT * FROM vw_EmprestimosVencidos;
        WHEN 4 THEN
            SELECT ID, Nome, Email, Telefone FROM Usuarios;
        WHEN 5 THEN
            SELECT ID, Nome, Cargo FROM Funcionarios;
        ELSE
            SELECT 'Opção inválida. Escolha: 1 = Livros, 2 = Histórico, 3 = Vencidos, 4 = Usuários, 5 = Funcionários' AS Mensagem;
    END CASE;
END //

DELIMITER ;

CALL MenuConsultaBiblioteca(1); -- Livros disponíveis
CALL MenuConsultaBiblioteca(2); -- Histórico de empréstimos
CALL MenuConsultaBiblioteca(3); -- Empréstimos vencidos
CALL MenuConsultaBiblioteca(4); -- Usuários
CALL MenuConsultaBiblioteca(5); -- Funcionários


