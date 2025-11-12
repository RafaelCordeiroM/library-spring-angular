package com.biblio.backend.config;

import com.biblio.backend.entity.Author;
import com.biblio.backend.entity.Book;
import com.biblio.backend.entity.Category;
import com.biblio.backend.repository.AuthorRepository;
import com.biblio.backend.repository.BookRepository;
import com.biblio.backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class DataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void run(String... args){
        if(categoryRepository.count() == 0){
            System.out.println("Inserting Inital Data...");
            createSampleData();
        }else{
            System.out.println("DB has data already...");
        }
    }
    private void createSampleData() {
        // === 1. CATEGORIAS ===
        Category ficcao = Category.builder()
                .name("Ficção Científica")
                .description("Livros de ficção científica, futurismo e tecnologia")
                .build();

        Category romance = Category.builder()
                .name("Romance")
                .description("Histórias de amor, drama e relacionamentos")
                .build();

        Category terror = Category.builder()
                .name("Terror")
                .description("Suspense, horror e mistério")
                .build();

        Category fantasia = Category.builder()
                .name("Fantasia")
                .description("Mundos mágicos, dragões e aventuras épicas")
                .build();

        Category naoFiccao = Category.builder()
                .name("Não Ficção")
                .description("Biografias, ciência, história e ensaios")
                .build();

        categoryRepository.saveAll(java.util.List.of(ficcao, romance, terror, fantasia, naoFiccao));

        // === 2. AUTORES ===
        Author asimov = Author.builder()
                .name("Isaac Asimov")
                .nationality("Russo-Americano")
                .birthYear(1920)
                .biography("Autor de Fundação e Eu, Robô. Um dos maiores da FC.")
                .build();

        Author austen = Author.builder()
                .name("Jane Austen")
                .nationality("Britânica")
                .birthYear(1775)
                .biography("Autora de Orgulho e Preconceito e Razão e Sensibilidade.")
                .build();

        Author king = Author.builder()
                .name("Stephen King")
                .nationality("Americano")
                .birthYear(1947)
                .biography("Mestre do terror. Autor de O Iluminado, It, Carrie.")
                .build();

        Author tolkien = Author.builder()
                .name("J.R.R. Tolkien")
                .nationality("Britânico")
                .birthYear(1892)
                .biography("Criador da Terra Média. Autor de O Senhor dos Anéis.")
                .build();

        authorRepository.saveAll(java.util.List.of(asimov, austen, king, tolkien));

        // === 3. LIVROS ===
        Book fundacao = Book.builder()
                .title("Fundação")
                .isbn("9780553293350")
                .publicationYear(1951)
                .edition("1ª Edição")
                .pages(320)
                .summary("A queda do Império Galáctico e o plano para salvar a civilização.")
                .coverImageUrl("https://example.com/fundacao.jpg")
                .category(ficcao)
                .totalCopies(5)
                .availableCopies(3)
                .build();

        Book orgulho = Book.builder()
                .title("Orgulho e Preconceito")
                .isbn("9780141439518")
                .publicationYear(1813)
                .edition("Clássica")
                .pages(432)
                .summary("Cinco irmãs em busca de amor e casamento na Inglaterra do século XIX.")
                .coverImageUrl("https://example.com/orgulho.jpg")
                .category(romance)
                .totalCopies(10)
                .availableCopies(7)
                .build();

        Book iluminado = Book.builder()
                .title("O Iluminado")
                .isbn("9780307743657")
                .publicationYear(1977)
                .edition("1ª Edição")
                .pages(688)
                .summary("Um hotel isolado, um inverno rigoroso e um homem com poderes psíquicos.")
                .coverImageUrl("https://example.com/iluminado.jpg")
                .category(terror)
                .totalCopies(4)
                .availableCopies(2)
                .build();

        Book senhorAneis = Book.builder()
                .title("O Senhor dos Anéis")
                .isbn("9780544003412")
                .publicationYear(1954)
                .edition("Edição de Luxo")
                .pages(1178)
                .summary("Uma jornada épica para destruir o Um Anel e salvar a Terra Média.")
                .coverImageUrl("https://example.com/senhor-dos-aneis.jpg")
                .category(fantasia)
                .totalCopies(3)
                .availableCopies(1)
                .build();

        // Relacionamentos
        fundacao.addAuthor(asimov);
        orgulho.addAuthor(austen);
        iluminado.addAuthor(king);
        senhorAneis.addAuthor(tolkien);

        // Salva tudo
        bookRepository.saveAll(java.util.List.of(fundacao, orgulho, iluminado, senhorAneis));
    }
}
