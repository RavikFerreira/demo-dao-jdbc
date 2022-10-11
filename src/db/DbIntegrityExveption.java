package db;

import java.io.Serial;

public class DbIntegrityExveption extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public DbIntegrityExveption(String msg){
        super(msg);
    }

}
