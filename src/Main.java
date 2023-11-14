import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в игру \"Угадай число\"! \n");

        System.out.println("Существует два режима:\n1. Вы угадываете число загаданное компьютером\n2. Компьютер угадывает загаданное вами число\n");


        for (int i = 0; i <= 1; i--) {

            System.out.print("Введите номер нужного режима: ");
            String GameMode = scanner.nextLine();

            switch (GameMode) {

                case "1":

                    GuessingGameUser_DifficultyLevel();

                case "2":

                    GuessingGameComputer(15, 7, 7, 1);

                    GuessingGameComputer(10, 10, 10, 2);

                    GuessingGameComputer(5, 15, 15, 3);

                    System.exit(0);


                default:

                    System.out.println("Неверный ввод!");

            }
        }
    }

    // Создаем основной метод для режима где должен угадывать игрок
    public static void GuessingGameUser(int min, int max, int attemtps, int level) {
        Scanner scanner = new Scanner(System.in);

        int random = Random(min, max);

        System.out.println("Уровень " + level + " \nЧисло загадано в диапозоне " + min + "-" + max);

        System.out.println("        У вас " + attemtps + " попыток");

        System.out.print("Попробуйте угадать число: ");


        while (attemtps >= 0) {
            attemtps--;

            int number = scanner.nextInt();

            if (number == random) {
                System.out.println("\nВы отгадали число.\nПоздравляем!\n");
                break;

                // Как только у игрока заканчиваются попытки, это условие сообщает о том какое было загаданное число и останавливает код
            } else if (attemtps == 0) {

                System.out.println("\nВы проиграли!\nЗагаданное число было: " + random);
                System.exit(0);

                //Данное условие работает тогда когда игрок не отгадывает число
            } else {
                System.out.println("\n        Осталось попыток: " + attemtps);

                // Если игрок ввел число которое находится в диапазоне +- пяти от загаданного числа то система ему сообщяет о том что он близок к загаданному числу
                if (number < random && random - number <= 5) {
                    System.out.println("Вы совсем близко!");

                }
                if (number > random && number - random <= 5) {
                    System.out.println("Вы совсем близко!");

                    // Если загаданное число больше либо меньше введенного числа, то система сообщает об этом пользователю
                }
                if (number < random) {
                    System.out.println("Загаданное число больше вашего");

                } else {
                    System.out.println("Загаданное число меньше вашего");

                }
                System.out.print("Попробуйте еще раз: ");
            }
        }
    }

    // Создаем метод который для каждого уровня сложности выставляет свои параметры игры
    public static void GuessingGameUser_DifficultyLevel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nЛегкий\nСредний\nСложный");

        for (int i = 0; i <= 1; i--) {
            System.out.print("Выберете сложность: ");
            String DifficultyLevel = scanner.nextLine();

            switch (DifficultyLevel) {

                case "Легкий":

                    GuessingGameUser(1, 50, 15, 1);

                    GuessingGameUser(1, 100, 10, 2);

                    GuessingGameUser(1, 150, 7, 3);

                    System.out.println("Вы прошли легкий уровень");

                    System.exit(0);

                case "Средний":

                    GuessingGameUser(1, 200, 10, 1);

                    GuessingGameUser(1, 350, 7, 2);

                    GuessingGameUser(1, 500, 5, 3);

                    System.out.println("Вы прошли средний уровень");

                    System.exit(0);

                case "Сложный":

                    GuessingGameUser(1, 200, 7, 1);

                    GuessingGameUser(1, 500, 5, 2);

                    GuessingGameUser(1, 1000, 3, 3);

                    System.out.println("Вы прошли сложный уровень");

                    System.exit(0);

                default:
                    System.out.println("Неверный ввод!");


            }
        }
    }


    // Создаем метод в котором компьютер отгадывает ваше загаданное число
    public static void GuessingGameComputer(int range, int attempts, int attempts3, int level) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Уровень " + level);

        System.out.println("У компьютера " + attempts + " попыток");

        System.out.print("Загадайте любое число: ");
        int TheHiddenNumber = scanner.nextInt();

        while (TheHiddenNumber <= 0) {

            System.out.print("\nЗагаданное число не может быть меньше единицы!\nЗагадайте число повторно: ");
            TheHiddenNumber = scanner.nextInt();

        }

        System.out.println("Отлично, теперь компьютер попробует отгадать ваше число\n");

        int[] list = new int[attempts3];

        int attempts2 = 0;

        // Цикл для того что бы компьютер не повторял свои числа, жалко что он не работает
        while (attempts >= 0) {

            int ComputerNums = Random(TheHiddenNumber - range, TheHiddenNumber + range);

            list[attempts2] = ComputerNums;

            for (int i = 0; i < attempts2; i++) {

                while (list[i] == ComputerNums) {

                    ComputerNums = Random(TheHiddenNumber - range, TheHiddenNumber + range);

                }

            }

            // Создаем цикл для того чтобы компьютер не выводил число меньше 1
            while (ComputerNums <= 0) {

                ComputerNums = Random(TheHiddenNumber - range, TheHiddenNumber + range);

            }

            // Замораживаем время между выводом чисел
            try {

                Thread.sleep(1000);

                System.out.println("Компьютер: " + ComputerNums);

            } catch (InterruptedException x) {
            }

            attempts--;
            attempts2++;

            if (ComputerNums == TheHiddenNumber) {

                System.out.println("\nВы проиграли!\nКомпьютер потратил попыток: " + attempts2);

                System.exit(0);

            } else if (ComputerNums != TheHiddenNumber && attempts == 0) {

                System.out.println("\nПоздравляем, вы выиграли!\nКомьютер не отгадал ваше число.\n");

                break;

            }
        }
    }

    // Создаем метод который будет в определенном диапазоне(min, max) генерировать случайные числа
    public static int Random(int min, int max) {

        return (int) (Math.random() * ((max - min) + 1)) + min;

    }
}