# Bingo
Esse repositório trata-se de um projeto feito para apresentação, e o mesmo foi utilizado durante todo o desenvolvimento e ficará aqui para fins de estudo.
O projeto é um jogo de bingo, desenvolvido com Sockets, Threads e Banco de Dados, na linguagem de programação Java.
O ambiente utilizado foi o Eclipse, e o código poderia estar melhor otimizado, porém a otimização não foi feito devido à falta de tempo.
Conceitos abordados:
  - Cliente
  - Servidor
  - Banco de Dados

# Funcionalidades

  - Ranking de jogadores
  - Sistema de cadastro e sessão de jogadores
  - Jogo completo e funcional
  - Gerenciador de partidas


O jogo é iniciado quando existem jogadores suficientes. O mínimo de jogadores está configurado para um, no entanto, você pode facilmente alterar isso no código fonte. Assim que o servidor é iniciado, uma contagem regressiva acontece para a inicialização de um novo jogo. Enquanto o jogo não começa, os jogadores estão aptos para entrar na partida. Após o jogo começar, não é mais possível entrar na partida e os jogadores conectados recebem as suas cartelas. Só é possível pedir Bingo quando o jogador marcar todos os números, no entanto, se os números marcados não tiverem sido divulgados ainda pelo servidor, o mesmo não será aceito. É responsabilidade do jogador marcar os números sorteados corretamente.
Após o término de uma partida, os jogadores são informados do vencedor, ou se não houve vencedores, e então, o servidor aguarda 10 segundos pra iniciar uma nova contagem regressiva. As cartelas dos jogadores são resetadas ao fim do jogo.


# Autores
  - Gustavo Ifanger
  - Felipe Ferreira