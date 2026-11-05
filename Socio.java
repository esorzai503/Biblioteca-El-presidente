public class Socio {

    public final int maxPrestamos = 2;

    private String nombre;
    private int numeroSocio;
    private int contrasena;
    private int librosPrestados;

    public Socio(String nombre, int numeroSocio, int contrasena) {
        this.nombre = nombre;
        this.numeroSocio = numeroSocio;
        this.contrasena = contrasena;
        this.librosPrestados = 0;

    }

    public boolean comprobarContrasena(int pin) {
        boolean esCorrecto = false;

        if (pin == contrasena) {
            esCorrecto = true;
        }
        if (pin != contrasena) {
            esCorrecto = false;
        }

        return esCorrecto;
    }

    public boolean puedePedir() {
        boolean pide = false;

        if (librosPrestados < maxPrestamos) {
            pide = true;
        }
        if (librosPrestados == maxPrestamos) {
            pide = false;
        }
        return pide;
    }

    public void pedirLibro(Libro libro) {

        if (puedePedir() == true) {
            if (Biblioteca.prestar(libro)) {
                librosPrestados++;
                System.out.println("Préstamo realizado: ahora tienes \"" + libro.getTitulo() + "\".");
            } else {
                System.out.println("El libro ya está prestado.");
            }
        } else {
            System.out.println("Has alcanzado el límite de préstamos.");
        }
    }

    public void devolverLibro(Libro libro) {
        if (libro.isDisponible()) {
            System.out.println("Ese libro no está prestado.");
        } else {
            Biblioteca.devolver(libro);
            if (librosPrestados > 0) {
                librosPrestados--;
            }
            System.out.println("Libro devuelto: \"" + libro.getTitulo() + "\".");
        }
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Número de socio: " + numeroSocio);
        System.out.println("Libros prestados: " + librosPrestados);
    }

    public int getMaxPrestamos() {
        return maxPrestamos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public int getContrasena() {
        return contrasena;
    }

    public void setContrasena(int contrasena) {
        this.contrasena = contrasena;
    }

    public int getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(int librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "\n Número de Socio: " + this.numeroSocio + "\n Número de libros prestados: " + this.librosPrestados ;
    }

    

}