-- Franquicias de ejemplo
INSERT INTO franquicias (nombre) 
SELECT 'McDonald''s' WHERE NOT EXISTS (SELECT 1 FROM franquicias WHERE nombre = 'McDonald''s');

INSERT INTO franquicias (nombre) 
SELECT 'Subway' WHERE NOT EXISTS (SELECT 1 FROM franquicias WHERE nombre = 'Subway');

INSERT INTO franquicias (nombre) 
SELECT 'KFC' WHERE NOT EXISTS (SELECT 1 FROM franquicias WHERE nombre = 'KFC');

-- Sucursales de ejemplo para McDonald's
INSERT INTO sucursales (nombre, franquicia_id)
SELECT 'McDonald''s Centro', id FROM franquicias WHERE nombre = 'McDonald''s'
AND NOT EXISTS (SELECT 1 FROM sucursales WHERE nombre = 'McDonald''s Centro' AND franquicia_id = (SELECT id FROM franquicias WHERE nombre = 'McDonald''s'));

INSERT INTO sucursales (nombre, franquicia_id)
SELECT 'McDonald''s Norte', id FROM franquicias WHERE nombre = 'McDonald''s'
AND NOT EXISTS (SELECT 1 FROM sucursales WHERE nombre = 'McDonald''s Norte' AND franquicia_id = (SELECT id FROM franquicias WHERE nombre = 'McDonald''s'));

INSERT INTO sucursales (nombre, franquicia_id)
SELECT 'McDonald''s Sur', id FROM franquicias WHERE nombre = 'McDonald''s'
AND NOT EXISTS (SELECT 1 FROM sucursales WHERE nombre = 'McDonald''s Sur' AND franquicia_id = (SELECT id FROM franquicias WHERE nombre = 'McDonald''s'));

-- Sucursales de ejemplo para Subway
INSERT INTO sucursales (nombre, franquicia_id)
SELECT 'Subway Plaza Mayor', id FROM franquicias WHERE nombre = 'Subway'
AND NOT EXISTS (SELECT 1 FROM sucursales WHERE nombre = 'Subway Plaza Mayor' AND franquicia_id = (SELECT id FROM franquicias WHERE nombre = 'Subway'));

INSERT INTO sucursales (nombre, franquicia_id)
SELECT 'Subway Centro Comercial', id FROM franquicias WHERE nombre = 'Subway'
AND NOT EXISTS (SELECT 1 FROM sucursales WHERE nombre = 'Subway Centro Comercial' AND franquicia_id = (SELECT id FROM franquicias WHERE nombre = 'Subway'));

-- Sucursales de ejemplo para KFC
INSERT INTO sucursales (nombre, franquicia_id)
SELECT 'KFC Avenida Principal', id FROM franquicias WHERE nombre = 'KFC'
AND NOT EXISTS (SELECT 1 FROM sucursales WHERE nombre = 'KFC Avenida Principal' AND franquicia_id = (SELECT id FROM franquicias WHERE nombre = 'KFC'));

INSERT INTO sucursales (nombre, franquicia_id)
SELECT 'KFC Terminal', id FROM franquicias WHERE nombre = 'KFC'
AND NOT EXISTS (SELECT 1 FROM sucursales WHERE nombre = 'KFC Terminal' AND franquicia_id = (SELECT id FROM franquicias WHERE nombre = 'KFC'));

-- Productos para McDonald's Centro
INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Big Mac', 100, id FROM sucursales WHERE nombre = 'McDonald''s Centro'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Big Mac' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Centro'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'McPollo', 80, id FROM sucursales WHERE nombre = 'McDonald''s Centro'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'McPollo' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Centro'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Papas Fritas', 150, id FROM sucursales WHERE nombre = 'McDonald''s Centro'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Papas Fritas' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Centro'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Helado', 50, id FROM sucursales WHERE nombre = 'McDonald''s Centro'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Helado' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Centro'));

-- Productos para McDonald's Norte
INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Big Mac', 75, id FROM sucursales WHERE nombre = 'McDonald''s Norte'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Big Mac' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Norte'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'McPollo', 65, id FROM sucursales WHERE nombre = 'McDonald''s Norte'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'McPollo' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Norte'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Papas Fritas', 120, id FROM sucursales WHERE nombre = 'McDonald''s Norte'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Papas Fritas' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Norte'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'McFlurry', 40, id FROM sucursales WHERE nombre = 'McDonald''s Norte'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'McFlurry' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Norte'));

-- Productos para McDonald's Sur
INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Big Mac', 90, id FROM sucursales WHERE nombre = 'McDonald''s Sur'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Big Mac' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Sur'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Cuarto de Libra', 70, id FROM sucursales WHERE nombre = 'McDonald''s Sur'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Cuarto de Libra' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Sur'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Papas Fritas', 130, id FROM sucursales WHERE nombre = 'McDonald''s Sur'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Papas Fritas' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Sur'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Refresco', 200, id FROM sucursales WHERE nombre = 'McDonald''s Sur'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Refresco' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'McDonald''s Sur'));

-- Productos para Subway Plaza Mayor
INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Sub de Pollo', 80, id FROM sucursales WHERE nombre = 'Subway Plaza Mayor'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Sub de Pollo' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'Subway Plaza Mayor'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Sub de Atún', 40, id FROM sucursales WHERE nombre = 'Subway Plaza Mayor'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Sub de Atún' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'Subway Plaza Mayor'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Sub Vegetal', 30, id FROM sucursales WHERE nombre = 'Subway Plaza Mayor'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Sub Vegetal' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'Subway Plaza Mayor'));

INSERT INTO productos (nombre, stock, sucursal_id)
SELECT 'Cookies', 100, id FROM sucursales WHERE nombre = 'Subway Plaza Mayor'
AND NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Cookies' AND sucursal_id = (SELECT id FROM sucursales WHERE nombre = 'Subway Plaza Mayor'));

-- Para reducir la longitud, se sigue el mismo patrón para el resto de productos