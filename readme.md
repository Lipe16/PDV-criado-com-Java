# JPDV
Sistema de vendas desenvolvido com Java WEB, inspirado nas aulas do Prof Sérgio Delfino no youtube

> ⚠️ **Atenção:** Este sistema não é adequado para uso comercial ou em ambientes de produção, pois foi desenvolvido há anos, durante os meus primeiros passos na programação, e encontra-se desatualizado.


### 🛠️ Passos para executar o sistema:

1. **Configure o ambiente:**
   - Utilize **Tomcat 8.5** como servidor de aplicação.
   - Certifique-se de que a versão do Java instalada é o **JDK 21**.

2. **Configure o banco de dados:**
   - Este sistema utiliza o banco de dados **MySQL**.
   - As configurações do banco podem ser encontradas no arquivo:
     - `src/main/resources/hibernate.cfg.xml`
   - Verifique e ajuste as informações de conexão conforme necessário (URL, usuário, senha, etc.).

3. **Execute a aplicação:**
   - Inicie o servidor com o projeto configurado.

4. **Teste o login:**
   - Use as seguintes credenciais para testar:
     - **Usuário:** `11111111111`
     - **Senha:** `1`

5. **Problema com o login?**
   - Se aparecer a mensagem de **"Senha inválida"**, siga este passo:
     - Execute o arquivo SQL localizado na raiz do projeto chamado `import.sql`.

> 💡 **Dica:** Após executar o `import.sql`, tente fazer login novamente.


Imagem do PDV
![PDV](https://image.ibb.co/mPPGsn/JSF.png)

Este projeto foi escrito com Eclipse usando Maven, hibernate, MySql, JSF, PrimeFaces dentre outros. Inicei
ele quando comecei a aprender Java para WEB com as aulas do prof Sérgio( [Canal no Youtube de Sérgio](https://www.youtube.com/channel/UCJdtabTp9TXaHxdYrAa2j0A) ),
e pretendo ir melhorando ele com passar do tempo e o adequando com funções fiscais.


Outros links úteis:

Filipe Ferreira - https://www.facebook.com/filipe.ferreira.5030927

vídeo do projeto no YOUTUBE - https://www.youtube.com/watch?v=COhp0HBeIwk


-Este projeto usa Eclipse, maven(para baixar suas dependencias), JDK 8+, Tom cat 8 ou 8.5, banco Mysql(você pode configurar nos arquivos do hibernate), sabendo usar estas tecnologias citadas aqui você conseguirá roda-lo no seu ambiente de trabalho...




Autenticação
![Autenticação](https://image.ibb.co/dneSyS/autenticacao.png)


Menu Dock
![Autenticação](https://image.ibb.co/hK7857/menuDock.png)


Componentes Bonitos
![Componentes Bonitos](https://image.ibb.co/hTbPdS/Varios_componentes.png)
