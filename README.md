# MedConnect – Projeto Integrador

- `MedConnect/` – aplicação Java Swing (projeto Ant do NetBeans)
- `MedConnectTests/` – projeto de testes JUnit 5 (Maven, abre no NetBeans)
- `docs/` – plano de testes

## Executar os testes
NetBeans: File > Open Project > `MedConnectTests` > Test Project (Alt+F6).
Terminal: `cd MedConnectTests && mvn test`

O projeto de testes compila diretamente os fontes de `../MedConnect/src`
(model, validator, exception e util), sem depender de banco ou interface.
