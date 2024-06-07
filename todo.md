# TODO
- [] Implementar a minha propria hash table (pra não ter que usar a do java)
- [] Leitura e escrita de arquivo txt em java
- [] Ler o texto automaticamente a partir de um txt
- [] Criar a lista de keywords automaticamente a partir de um txt
- [] Criar uma classe separada IndiceRemissivo para deixar a Main mais clean
- [] Comentar todo o codigo
- [] Testar com outros textos



# Planejamento
1. Ler o arquivo de keywords e criar um hash composto pela keyword + um array vazio que vai ser preenchido com inteiros depois.
2. Percorrer o texto e verificar se a palavra é uma keyword.
    obs: Pra checar se é uma keyword basta pesquisar no hash criado no primeiro passo
3. Se for uma keyword, dá um push no array do hash o numero da linha que a palavra foi encontrada
4. ao mesmo tempo, coloca a palavra encontrada na arvore binária
5. na hora de printar, usar o metodo getNodesAlphabetically da arvore binaria pra poder retornar os elementos num array
   e a partir daí, iterar nesses elementos, pesquisando cada um na tabela hash para obter as listas de linhas.
6. Só printar bonitinho depois disso