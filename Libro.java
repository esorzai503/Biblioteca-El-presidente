public class Libro {

    private String titulo;
    private String autor;
    private int anioPublicacion;
    private boolean disponible;
    private int vecesPrestado;

    public Libro(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
        this.vecesPrestado = 0;
    }

    public void mostrarInfo() {
        if (disponible) {
            System.out.printf("%s - %s (%d)\n", titulo, autor, anioPublicacion);
            System.out.println("Estado: Disponible");

        } else if (disponible == false) {
            System.out.printf("%s - %s (%d)\n", titulo, autor, anioPublicacion);
            System.out.println("Estado: Prestado");
            
        }

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }

    public void setVecesPrestado(int vecesPrestado) {
        this.vecesPrestado = vecesPrestado;
    }


}