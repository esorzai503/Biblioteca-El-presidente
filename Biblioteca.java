import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Biblioteca {

    public static void main(String[] args) {

        int intentos = 0;

        Scanner s = new Scanner(System.in).useLocale(Locale.US);
        Libro l1 = new Libro("La metamorfósis", "Fran Kafka", 1915);
        Libro l2 = new Libro("La peste", "Albert Camus", 1947);
        Libro l3 = new Libro("El manifiesto comunista", "Karl Marx, Friedrich Engels", 1848);
        Socio s1 = new Socio("Lucía", 101, 1001);
        Socio s2 = new Socio("Marcos", 102, 1002);

        System.out.println("--- SOCIOS ---");
        System.out.printf("%d - %s\n", s1.getNumeroSocio(), s1.getNombre());
        System.out.printf("%d - %s\n", s2.getNumeroSocio(), s2.getNombre());
        System.out.print("Introduce tu número de socio: ");

        int numero = lectura(s);
        Socio socio = null;

        while (socio == null) {
            if (numero == s1.getNumeroSocio()) {
                socio = s1;
            } else if (numero == s2.getNumeroSocio()) {
                socio = s2;
            } else {
                System.out.print("Ese socio no existe, inténtalo de nuevo: ");
                numero = lectura(s);
            }
        }

        boolean acceso = false;

        while (!acceso && intentos < 3) {
            System.out.print("Contraseña: ");
            if (socio.comprobarContrasena(lectura(s))) {
                acceso = true;
            } else {
                intentos++;
                if (intentos < 3) {
                    System.out.printf("Contraseña incorrecta. Te quedan %d intentos.\n", 3 - intentos);
                }
            }
        }

        if (acceso) {
            menuSesion(l1, l2, l3, socio, s);
        } else {
            System.out.println("Acceso denegado. Has agotado los 3 intentos.");
        }

        s.close();
    }

    public static boolean prestar(Libro libro) {
        boolean sePresta = false;
        int vecesPrestado = libro.getVecesPrestado();

        if (libro.isDisponible() == true) {
            libro.setDisponible(false);
            libro.setVecesPrestado(vecesPrestado++); 
            sePresta = true;
        }
    

        return sePresta;
    }

    public static void devolver(Libro libro) {
        libro.setDisponible(true);
    }

    public static int lectura(Scanner s) {
        int lectura = 0;
        boolean valido = false;

        while (!valido) {
            try {

                lectura = s.nextInt();
                valido = true;

            } catch (InputMismatchException ime) {
                System.out.println("ERROR**: Escribe un dato que sea  válido.");
                s.next();
            } catch (NumberFormatException nfe) {
                System.out.println("ERROR**: Introduzca un número válido entero.");
            }
        }

        return lectura;
    }

    public static void mostrarTodos(Libro l1, Libro l2, Libro l3) {
        System.out.print("1.");
        l1.mostrarInfo();
        System.out.println();

        System.out.print("2.");
        l2.mostrarInfo();
        System.out.println();

        System.out.print("3.");
        l3.mostrarInfo();
        System.out.println();
    }

    public static Libro elegirLibro(int num, Libro l1, Libro l2, Libro l3) {
        if (num == 1)
            return l1;
        else if (num == 2)
            return l2;
        else
            return l3;
    }

    public static int pedirNumeroLibro(Libro l1, Libro l2, Libro l3, Scanner s) {
        System.out.println("--- LIBROS DE LA BIBLIOTECA ---");
        mostrarTodos(l1, l2, l3);
        System.out.print("¿Qué libro quieres? ");
        int num = lectura(s);

        while (num < 1 || num > 3) {
            System.out.print("Número no válido, elige 1, 2 o 3: ");
            num = lectura(s);
        }
        return num;
    }

    public static void menuSesion(Libro l1, Libro l2, Libro l3, Socio socio, Scanner s) {
        int opcion = 0;

        System.out.println("Bienvenido/a, " + socio.getNombre());

        do {
            System.out.println("1. Ver todos los libros");
            System.out.println("2. Pedir un libro");
            System.out.println("3. Devolver un libro");
            System.out.println("4. Ver mis datos de socio");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = lectura(s);

            switch (opcion) {
                case 1:
                    System.out.println("--- LIBROS DE LA BIBLIOTECA ---");
                    mostrarTodos(l1, l2, l3);
                    break;
                case 2:
                    socio.pedirLibro(elegirLibro(pedirNumeroLibro(l1, l2, l3, s), l1, l2, l3));
                    break;
                case 3:
                    socio.devolverLibro(elegirLibro(pedirNumeroLibro(l1, l2, l3, s), l1, l2, l3));
                    break;
                case 4:
                    socio.mostrarDatos();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Opción no válida, inténtalo de nuevo.");
            }

        } while (opcion != 5);


        System.out.printf("Te quedan %d libros sin devolver.\n", socio.getLibrosPrestados());

        int v1 = l1.getVecesPrestado();
        int v2 = l2.getVecesPrestado();
        int v3 = l3.getVecesPrestado();

        if (v1 == 0 && v2 == 0 && v3 == 0) {
            System.out.println("No se ha prestado ningún libro en esta sesión.");
        } else if (v1 > v2 && v1 > v3) {
            System.out.printf("El libro más prestado ha sido \"%s\" (%d veces).\n", l1.getTitulo(), v1);
        } else if (v2 > v1 && v2 > v3) {
            System.out.printf("El libro más prestado ha sido \"%s\" (%d veces).\n", l2.getTitulo(), v2);
        } else if (v3 > v1 && v3 > v2) {
            System.out.printf("El libro más prestado ha sido \"%s\" (%d veces).\n", l3.getTitulo(), v3);
        } else {
            System.out.println("Hay un empate entre los libros más prestados.");
        }

        System.out.println("¡Hasta pronto!");
    }

}