Projeto base de automação de API com restAssured

------------------------------------------------------------------------------------------------

API publica (GET) - https://api.chucknorris.io/

https://api.chucknorris.io/jokes/categories
- statusCode e tempo de resposta    
- contrato							

https://api.chucknorris.io/jokes/random?category={category}
- statuscode e tempo de resposta 
- contrato 
- categoria valida  
- categoria inválida 

https://api.chucknorris.io/jokes/random
- statusCode e tempo de resposta 
- contrato 

https://api.chucknorris.io/jokes/search?query={query}
- statuscode e tempo de resposta 
- contrato
- busca de termo valido
- busca de termo inválido/inexistente
---------------------------------------------------------------



- clean test -Dtest=ContractTests (contracts //testes de contrato)
- clean test -Dtest=AllTests (alltests //testes positivos e negativos)

- allure:serve - relatório
