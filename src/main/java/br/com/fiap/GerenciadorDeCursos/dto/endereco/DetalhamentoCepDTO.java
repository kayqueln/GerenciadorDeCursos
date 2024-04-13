package br.com.fiap.GerenciadorDeCursos.dto.endereco;

public record DetalhamentoCepDTO(
         String cep,
         String logradouro,
         String complemento,
         String bairro,
         String localidade,
         String uf,
         String ibge,
         String gia,
         String ddd,
         String siafi
) {
}
