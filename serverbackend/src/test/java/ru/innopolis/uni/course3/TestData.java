package ru.innopolis.uni.course3;

import ru.innopolis.uni.course3.model.Book;
import ru.innopolis.uni.course3.model.Role;
import ru.innopolis.uni.course3.model.User;

import java.util.*;

/**
 *
 */
public class TestData {

    public static final int START_GLOBAL_SEQ = 100000;

    public static final int READER_ID = START_GLOBAL_SEQ;
    public static final int LIBRARIAN_ID = START_GLOBAL_SEQ + 1;
    public static final int BOOK1_ID = START_GLOBAL_SEQ + 2;

    public static final User READER = new User(READER_ID, "Reader", "reader@onlinelibrary.ru", "cb6e5c605babf1dbe9dff5b2bff4ae945c1b4dc3fb080b97b8736125b54789a3", new Date(), true, 0, Role.ROLE_USER);
    public static final User LIBRARIAN = new User(LIBRARIAN_ID, "Librarian", "librarian@onlinelibrary.ru", "d18aca8b42c65fe39edc3c376a97e7baccc62bfe418fc63fb2b0dac9c54c01fe", new Date(), true, 0, Role.ROLE_ADMIN);

    public static final Book BOOK1 = new Book(BOOK1_ID, "Шарль Перро", "Кот в сапогах", 2014, "Было у мельника три сына, и оставил он им, умирая, всего только мельницу, осла и кота...", 0);
    public static final Book BOOK2 = new Book(BOOK1_ID + 1, "Шарль Перро", "Золушка", 2015, "Жил-был один почтенный и знатный человек. Первая жена его умерла, и он женился во второй раз...", 0);
    public static final Book BOOK3 = new Book(BOOK1_ID + 2, "Шарль Перро", "Красная Шапочка", 2016, "Жила-была маленькая девочка. Была она скромная и добрая, послушная и работящая...", 0);
    public static final Book BOOK4 = new Book(BOOK1_ID + 3, "Ганс Христиан Андерсен", "Гадкий утёнок", 2014, "Хорошо было за городом! Стояло лето. На полях уже золотилась рожь, овес зеленел, сено было смётано в стога...", 0);
    public static final Book BOOK5 = new Book(BOOK1_ID + 4, "Ганс Христиан Андерсен", "Дикие лебеди", 2015, "Далеко-далеко, в той стране, куда улетают от нас на зиму ласточки, жил король...", 0);
    public static final Book BOOK6 = new Book(BOOK1_ID + 5, "Ганс Христиан Андерсен", "Дюймовочка", 2016, "Жила-была женщина; очень ей хотелось иметь ребёнка...", 0);
    public static final Book BOOK7 = new Book(BOOK1_ID + 6, "Гримм, братья Вильгем и Якоб","Белоснежка и семь гномов", 2014, "Было то в середине зимы, падали снежинки, точно пух с неба, и сидела королева у окошка..", 0);
    public static final Book BOOK8 = new Book(BOOK1_ID + 7, "Гримм, братья Вильгем и Якоб","Горшочек каши", 2015, "Жила-была одна девочка. Пошла девочка в лес за ягодами и встретила там старушку...", 0);
    public static final Book BOOK9 = new Book(BOOK1_ID + 8, "Гримм, братья Вильгем и Якоб","Бременские музыканты", 2016, "Много лет тому назад жил на свете мельник. И был у мельника осел...", 0);

    public static final List<Book> BOOKS = Arrays.asList(BOOK1, BOOK2, BOOK3, BOOK4, BOOK5, BOOK6, BOOK7, BOOK8, BOOK9);

    public static Book getCreatedBook() {
        return new Book(null, "Сказки народов мира", "Волшебная лампа Алладина", 2016, "В одном персидском городе жил когда-то бедный портной...", 0);
    }

    public static Book getUpdatedBook() {
        return new Book(BOOK1_ID, "Unknown author", "Untitled", 2017, "...", 0);
    }

    public static User getCreatedUser() {
        return new User(null, "Reader #2", "reader2@onlinelibrary.ru", "password", new Date(), true, 0, Role.ROLE_USER);
    }

    public static User getUpdatedUser() {
        return new User(READER_ID, "Reader #2", "reader2@onlinelibrary.ru", "password", new Date(), true, 0, Role.ROLE_USER);
    }

    private static final Comparator DEFAULT_COMPARATOR =
            (Object expected, Object actual) -> expected.toString().compareTo(actual.toString());

    public static <T> String as(List<T> list) {
        Collections.sort(list, DEFAULT_COMPARATOR);
        return list.toString();
    }

    public static <T> String as(T object) {
        return object.toString();
    }

}
