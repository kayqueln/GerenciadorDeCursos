Checkpoint 2
Contexto: Vocês foram contratados para desenvolver um sistema de gerenciamento de
cursos para uma universidade. Este sistema deve permitir a administração de cursos,
alunos, e professores, além de gerenciar as inscrições dos alunos nos cursos.
Objetivos e Requisitos:
1. JPA com Mapeamento One-to-Many e Many-to-Many
   Curso <--> Professores: Um curso pode ser ministrado por vários professores, e um
   professor pode ministrar vários cursos (Many-to-Many).
   Curso <--> Alunos: Um curso pode ter vários alunos inscritos, mas um aluno pode se
   inscrever em vários cursos (Many-to-Many).
   Curso <--> Matérias: Um curso contém várias matérias, mas uma matéria pertence a um
   único curso (One-to-Many).
2. JPA Cascade (Pelo Menos Três Tipos)
   Use CascadeType.PERSIST para persistir matérias quando um curso é persistido.
   Use CascadeType.REMOVE para remover todas as inscrições de um aluno quando este é
   removido.
   Use CascadeType.MERGE para atualizar as informações dos professores associados a um
   curso quando este é atualizado.
3. Fetch Lazy e Eager
   Configure o fetch type para LAZY nas matérias de um curso para melhorar a performance.
   Configure o fetch type para EAGER nas inscrições dos alunos em um curso, pois estas
   informações são frequentemente acessadas juntas.
4. Transactions (Com Dois Tipos de Propagation)
   Use Propagation.REQUIRED para as operações de inscrição de alunos em cursos para
   garantir atomicidade.
   Use Propagation.REQUIRES_NEW para operações de registro de novos cursos, para
   garantir que cada registro de curso seja uma transação separada.
5. Controller com API REST e DTO e Spring Validation
   Implemente endpoints CRUD para alunos, professores, e cursos.
   Use DTOs para transferir dados entre a API e os clientes.
   Utilize Spring Validation para validar as entradas nas requisições (ex.: nome do curso não
   pode ser vazio).
6. Um Controller com REST HATEOAS
   Implemente um endpoint que liste todos os cursos disponíveis com links HATEOAS para
   acessar detalhes do curso, professores relacionados, e alunos inscritos.
7. Tratamento de Erros com ControllerAdvice e Exception Handler
   Implemente um ControllerAdvice para tratar exceções comuns como
   EntityNotFoundException.
   Crie exceções customizadas para casos específicos, por exemplo, CourseFullException
   quando um aluno tenta se inscrever em um curso que já atingiu o limite de inscrições.
   Entregáveis:
8)
Validações Comuns
@NotNull: Usado para campos que não podem ser nulos.
@NotEmpty e @NotBlank: Usados para strings, onde @NotEmpty verifica se a string não é
nula e não é vazia, e @NotBlank adiciona uma verificação adicional de caracteres nãobrancos.
@Size: Usado para strings ou coleções, especificando o tamanho mínimo e/ou máximo.
@Email: Garante que o campo corresponda a um formato de e-mail válido.
@Positive e @PositiveOrZero: Usado para validar que um número é positivo ou zero, útil
para idades, quantidades, etc.
Casos de Validações para o Sistema de Gerenciamento de Cursos
AlunoDTO
Nome: @NotBlank, @Size(max = 100) para garantir que o nome do aluno seja fornecido e
não ultrapasse 100 caracteres.
Email: @NotBlank, @Email para garantir que um e-mail válido seja fornecido.
CursoDTO
Nome do Curso: @NotBlank, @Size(max = 150) para garantir que o nome do curso seja
fornecido e tenha um tamanho razoável.
Descrição: @Size(max = 1000) para descrições longas, mas com limite para evitar
excessos.
Data de Início: @FutureOrPresent para garantir que a data de início não seja no passado.
ProfessorDTO
Nome: @NotBlank, @Size(max = 100) para assegurar que o nome do professor seja
fornecido e não ultrapasse 100 caracteres.
Especialidade: @NotBlank para garantir que a especialidade do professor seja fornecida.
MatériaDTO
Nome: @NotBlank, @Size(max = 100) para garantir que o nome da matéria seja fornecido e
não ultrapasse 100 caracteres.
Descrição: @Size(max = 500) para permitir uma descrição detalhada, mas com um limite
para manter a concisão.
InscriçãoDTO
Curso Id: @NotNull para garantir que um curso seja especificado.
Aluno Id: @NotNull para garantir que um aluno seja especificado.
Data de Inscrição: @PastOrPresent para garantir que a inscrição não seja datada no futuro.
9) Validações:
   • Implementando Regras de Validação no Serviço de Aluno
   • Idade Mínima de 18 Anos: Calcule a idade a partir da data de nascimento fornecida
   e verifique se ela atende ao requisito de idade mínima.
   • Residência no País: Verifique se o endereço fornecido corresponde ao país
   esperado.
   • CPF Válido: Implemente ou utilize uma biblioteca existente para validar o formato e
   os dígitos do CPF.
   • Graduação Completa há pelo Menos 5 Anos: Verifique a data de conclusão da
   graduação.
   • Fluência em Inglês e Português: Isso pode ser representado por um campo
   booleano ou uma lista de idiomas falados.
   • Experiência em Tecnologia de pelo Menos 10 Anos: Calcule o período de
   experiência a partir das datas fornecidas.
   Código-fonte do projeto no GitHub.
   Documentação da API com Swagger
   Critérios de Avaliação:
   Adesão aos requisitos.
   Qualidade do código e uso adequado dos padrões de projeto.
   Documentação e facilidade de uso da API.
   Duração: A prova deve ser realizada em grupos de três pessoas durante uma semana.