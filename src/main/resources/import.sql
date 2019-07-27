INSERT INTO `clientes` (`activo`, `apellidos`, `created_at`, `direccion`, `email`, `nombre_contacto`, `nombre_empresa`, `nombres`, `notas`, `sitio_web`, `telefono_fijo`, `telefono_fijo2`, `telefono_movil`, `telefono_movil2`) VALUES (b'1', 'Portillo Orantes', '2019-02-17 00:00:00.000000', 'Santo Tomas', 'eugenio.alpha@gmail.com', 'Miguel Portillo', 'MKSoft', 'Miguel Eugenio', 'Notas', 'www.mksoft.com.sv', '22254384', '222232884', '78615687', '70207599');
INSERT INTO `clientes` (`activo`, `apellidos`, `created_at`, `direccion`, `email`, `nombre_contacto`, `nombre_empresa`, `nombres`, `notas`, `sitio_web`, `telefono_fijo`, `telefono_fijo2`, `telefono_movil`, `telefono_movil2`) VALUES (b'1', 'Gonzalez', '2019-02-17 00:00:00.000000', 'San Juan', '', 'Jimmy Gonzalez', '', 'Jimmy Gonzalez', '', '', '', '', '79741000', '');
INSERT INTO `clientes` (`activo`, `apellidos`, `created_at`, `direccion`, `email`, `nombre_contacto`, `nombre_empresa`, `nombres`, `notas`, `sitio_web`, `telefono_fijo`, `telefono_fijo2`, `telefono_movil`, `telefono_movil2`) VALUES (b'1', 'Orantes de Portillo', '2019-02-17 00:00:00.000000', 'San Jose Las Isletas', '', 'Reina de Portillo', 'La Campesina', 'Reina de Jesus', '', '', '', '', '78807158', '');

INSERT INTO `productos` (`created_at`, `descripcion`, `nombre`, `notas`) VALUES
('2019-03-29 00:00:00.000000', 'Quesillo Olancho Super Especial Huacal', 'Quesillo Olancho Super Especial Huacal', 'Quesillo Olancho Super Especial Huacal');
INSERT INTO `productos` (`created_at`, `descripcion`, `nombre`, `notas`) VALUES
('2019-03-29 00:00:00.000000', 'Marqueta de queso duro/blando', 'Marqueta Queso Duro Blando', 'Marqueta de queso duro/blando');

INSERT INTO `proveedores` (`created_at`, `direccion`, `nombre_contacto`, `nombre_empresa`, `notas`, `telefono_fijo`, `telefono_fijo2`, `telefono_movil`, `telefono_movil2`) VALUES
('2019-03-29 00:00:00.000000', 'Olancho, Honduras', 'Donalis Alas', 'Lacteos Olancho', 'Lacteos Olancho, Honduras, de Donalis Alas', '25649871', NULL, '78920015', NULL);

INSERT INTO `lotes` (`cantidad`, `created_at`, `fecha_entrada`, `lote`, `producto_id`, `proveedor_id`) VALUES
(500, '2019-03-29 00:00:00.000000', '2019-03-30 00:00:00.000000', '040319', 1, 1);
INSERT INTO `lotes` (`cantidad`, `created_at`, `fecha_entrada`, `lote`, `producto_id`, `proveedor_id`) VALUES
(10, '2019-03-29 00:00:00.000000', '2019-03-30 00:00:00.000000', '040319', 2, 1);
