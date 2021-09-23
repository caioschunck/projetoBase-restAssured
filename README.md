Projeto base de automação de API com restAssured

------------------------------------------------------------------------------------------------

API publica (GET) - https://api.chucknorris.io/

https://api.chucknorris.io/jokes/categories
- statuscode e tempo de resposta    
- schema							

https://api.chucknorris.io/jokes/random?category={category}
- statuscode e tempo de resposta 
- schema 
- categoria valida  
- categoria inválida 

https://api.chucknorris.io/jokes/random
- statuscode e tempo de resposta 
- schema 

https://api.chucknorris.io/jokes/search?query={query}
- statuscode e tempo de resposta 
- schema
- busca de termo valido
- busca de termo inválido/inexistente
---------------------------------------------------------------



- clean test -Dtest=SchemaTests (schema //testes de schema)
- clean test -Dtest=AllTests (alltests //testes positivos e negativos)

- allure:serve - relatório
