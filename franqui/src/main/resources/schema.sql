-- Tabla de Franquicias
CREATE TABLE IF NOT EXISTS franquicias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_franquicias_nombre UNIQUE (nombre)
);

-- Tabla de Sucursales
CREATE TABLE IF NOT EXISTS sucursales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    franquicia_id INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_sucursales_franquicia FOREIGN KEY (franquicia_id) 
        REFERENCES franquicias(id) ON DELETE CASCADE,
    CONSTRAINT uk_sucursal_franquicia UNIQUE (nombre, franquicia_id)
);

-- Tabla de Productos
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    sucursal_id INT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_productos_sucursal FOREIGN KEY (sucursal_id) 
        REFERENCES sucursales(id) ON DELETE CASCADE,
    CONSTRAINT uk_producto_sucursal UNIQUE (nombre, sucursal_id)

);