INSERT INTO pdvdb.Estado(codigo, nome, sigla) VALUES(1, 'Minas Gerais', 'MG');


INSERT INTO pdvdb.Cidade(codigo, nome, estado_codigo) VALUES(1, 'Ipatinga', 1);


INSERT INTO pdvdb.Pessoa (codigo, bairro, celular, cep, complemento, cpf, email, nome, numero, rg, rua, telefone, cidade_codigo) VALUES(1, 'adminBairro', '(31)99999-9999', '123456', '', '111.111.111-11', '', 'Admin', 0, 'adminRG', 'adminRua', '', 1);

INSERT INTO pdvdb.Usuario (codigo, ativo, senha, tipo, pessoa_codigo) VALUES(1, 1, '1', 'A', 1);

