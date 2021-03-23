/*
Navicat MySQL Data Transfer

Source Server         : Sistema Escolar
Source Server Version : 50505
Source Host           : 50.31.177.71:3306
Source Database       : rhtyjofc_controlescolar

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-03-22 21:07:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for administrativos
-- ----------------------------
DROP TABLE IF EXISTS `administrativos`;
CREATE TABLE `administrativos` (
  `usuario` varchar(50) NOT NULL,
  `contrasena` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of administrativos
-- ----------------------------
INSERT INTO `administrativos` VALUES ('Administrador2', 'UniversidadVeracruzana');
INSERT INTO `administrativos` VALUES ('Administrador1', 'UniversidadVeracruzana');
INSERT INTO `administrativos` VALUES ('Administrador3', 'UniversidadVeracruzana');
INSERT INTO `administrativos` VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for calificaciones
-- ----------------------------
DROP TABLE IF EXISTS `calificaciones`;
CREATE TABLE `calificaciones` (
  `id_calificacion` int(11) NOT NULL AUTO_INCREMENT,
  `nota` int(11) DEFAULT NULL,
  `fk_estudiante` varchar(15) DEFAULT NULL,
  `fk_materia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_calificacion`),
  KEY `fk_estudiante` (`fk_estudiante`),
  KEY `fk_materia` (`fk_materia`)
) ENGINE=MyISAM AUTO_INCREMENT=77 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of calificaciones
-- ----------------------------
INSERT INTO `calificaciones` VALUES ('1', '10', 'EP201A01', '2659');
INSERT INTO `calificaciones` VALUES ('2', '9', 'EP201A01', '2660');
INSERT INTO `calificaciones` VALUES ('3', '10', 'EP201A01', '2661');
INSERT INTO `calificaciones` VALUES ('4', '9', 'EP201A01', '2262');
INSERT INTO `calificaciones` VALUES ('5', '9', 'EP201A01', '2263');
INSERT INTO `calificaciones` VALUES ('6', '8', 'EP201A01', '2664');
INSERT INTO `calificaciones` VALUES ('7', '8', 'EP201A02', '2659');
INSERT INTO `calificaciones` VALUES ('8', '10', 'EP201A02', '2660');
INSERT INTO `calificaciones` VALUES ('9', '9', 'EP201A02', '2661');
INSERT INTO `calificaciones` VALUES ('10', '9', 'EP201A02', '2262');
INSERT INTO `calificaciones` VALUES ('11', '10', 'EP201A02', '2263');
INSERT INTO `calificaciones` VALUES ('12', '9', 'EP201A02', '2664');
INSERT INTO `calificaciones` VALUES ('13', '10', 'EP201A03', '2659');
INSERT INTO `calificaciones` VALUES ('14', '9', 'EP201A03', '2660');
INSERT INTO `calificaciones` VALUES ('15', '8', 'EP201A03', '2661');
INSERT INTO `calificaciones` VALUES ('16', '9', 'EP201A03', '2262');
INSERT INTO `calificaciones` VALUES ('17', '9', 'EP201A03', '2263');
INSERT INTO `calificaciones` VALUES ('18', '10', 'EP201A03', '2664');
INSERT INTO `calificaciones` VALUES ('19', '8', 'EP201A04', '2659');
INSERT INTO `calificaciones` VALUES ('20', '8', 'EP201A04', '2660');
INSERT INTO `calificaciones` VALUES ('21', '9', 'EP201A04', '2661');
INSERT INTO `calificaciones` VALUES ('22', '8', 'EP201A04', '2262');
INSERT INTO `calificaciones` VALUES ('23', '9', 'EP201A04', '2263');
INSERT INTO `calificaciones` VALUES ('24', '9', 'EP201A04', '2664');
INSERT INTO `calificaciones` VALUES ('25', '10', 'EP201A05', '2659');
INSERT INTO `calificaciones` VALUES ('26', '8', 'EP201A05', '2660');
INSERT INTO `calificaciones` VALUES ('27', '9', 'EP201A05', '2661');
INSERT INTO `calificaciones` VALUES ('28', '10', 'EP201A05', '2262');
INSERT INTO `calificaciones` VALUES ('29', '9', 'EP201A05', '2263');
INSERT INTO `calificaciones` VALUES ('30', '8', 'EP201A05', '2664');
INSERT INTO `calificaciones` VALUES ('31', '9', 'EP301A01', '2400');
INSERT INTO `calificaciones` VALUES ('32', '8', 'EP301A01', '2401');
INSERT INTO `calificaciones` VALUES ('33', '9', 'EP301A01', '2402');
INSERT INTO `calificaciones` VALUES ('34', '8', 'EP301A01', '2402');
INSERT INTO `calificaciones` VALUES ('35', '9', 'EP301A01', '2403');
INSERT INTO `calificaciones` VALUES ('36', '10', 'EP301A01', '2404');
INSERT INTO `calificaciones` VALUES ('37', '8', 'EP301A01', '2405');
INSERT INTO `calificaciones` VALUES ('38', '9', 'EP301A02', '2400');
INSERT INTO `calificaciones` VALUES ('39', '8', 'EP301A02', '2401');
INSERT INTO `calificaciones` VALUES ('40', '7', 'EP301A02', '2402');
INSERT INTO `calificaciones` VALUES ('41', '10', 'EP301A02', '2403');
INSERT INTO `calificaciones` VALUES ('42', '8', 'EP301A02', '2404');
INSERT INTO `calificaciones` VALUES ('43', '9', 'EP301A02', '2405');
INSERT INTO `calificaciones` VALUES ('44', '9', 'EP301A03', '2400');
INSERT INTO `calificaciones` VALUES ('45', '8', 'EP301A03', '2401');
INSERT INTO `calificaciones` VALUES ('46', '9', 'EP301A03', '2402');
INSERT INTO `calificaciones` VALUES ('47', '9', 'EP301A03', '2403');
INSERT INTO `calificaciones` VALUES ('48', '9', 'EP301A03', '2404');
INSERT INTO `calificaciones` VALUES ('49', '10', 'EP301A03', '2405');
INSERT INTO `calificaciones` VALUES ('50', '9', 'EP301A04', '2400');
INSERT INTO `calificaciones` VALUES ('51', '9', 'EP301A04', '2401');
INSERT INTO `calificaciones` VALUES ('52', '10', 'EP301A04', '2402');
INSERT INTO `calificaciones` VALUES ('53', '10', 'EP301A04', '2403');
INSERT INTO `calificaciones` VALUES ('54', '9', 'EP301A04', '2404');
INSERT INTO `calificaciones` VALUES ('55', '8', 'EP301A04', '2405');
INSERT INTO `calificaciones` VALUES ('56', '8', 'EP301A05', '2400');
INSERT INTO `calificaciones` VALUES ('57', '9', 'EP301A05', '2401');
INSERT INTO `calificaciones` VALUES ('58', '10', 'EP301A05', '2402');
INSERT INTO `calificaciones` VALUES ('59', '9', 'EP301A05', '2403');
INSERT INTO `calificaciones` VALUES ('60', '10', 'EP301A05', '2404');
INSERT INTO `calificaciones` VALUES ('61', '8', 'EP301A05', '2405');
INSERT INTO `calificaciones` VALUES ('63', '9', 'EP301A06', '2401');
INSERT INTO `calificaciones` VALUES ('62', '10', 'EP301A06', '2400');
INSERT INTO `calificaciones` VALUES ('64', '9', 'EP301A06', '2402');
INSERT INTO `calificaciones` VALUES ('65', '8', 'EP301A06', '2403');
INSERT INTO `calificaciones` VALUES ('66', '10', 'EP301A06', '2404');
INSERT INTO `calificaciones` VALUES ('67', '8', 'EP301A06', '2405');
INSERT INTO `calificaciones` VALUES ('68', '9', 'EP301A07', '2400');
INSERT INTO `calificaciones` VALUES ('69', '10', 'EP301A07', '2401');
INSERT INTO `calificaciones` VALUES ('70', '8', 'EP301A07', '2402');
INSERT INTO `calificaciones` VALUES ('71', '7', 'EP301A07', '2403');
INSERT INTO `calificaciones` VALUES ('72', '7', 'EP301A07', '2404');
INSERT INTO `calificaciones` VALUES ('73', '9', 'EP301A07', '2405');
INSERT INTO `calificaciones` VALUES ('74', '10', 'EP301A08', '2400');
INSERT INTO `calificaciones` VALUES ('75', '9', 'EP301A08', '2401');
INSERT INTO `calificaciones` VALUES ('76', '9', 'EP301A08', '2402');

-- ----------------------------
-- Table structure for estudiantes
-- ----------------------------
DROP TABLE IF EXISTS `estudiantes`;
CREATE TABLE `estudiantes` (
  `matricula` varchar(15) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido_paterno` varchar(50) DEFAULT NULL,
  `apellido_materno` varchar(50) DEFAULT NULL,
  `fk_grupo` int(11) DEFAULT NULL,
  PRIMARY KEY (`matricula`),
  KEY `fk_grupo` (`fk_grupo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of estudiantes
-- ----------------------------
INSERT INTO `estudiantes` VALUES ('EP201A01', 'ABINADAD', 'MORALES', 'MONTAN', '21');
INSERT INTO `estudiantes` VALUES ('EP201A02', 'JUAN ALBERTO', 'RUIZ', 'ARIAS', '21');
INSERT INTO `estudiantes` VALUES ('EP201A03', 'ALAN DE JESUS', 'RAMIREZ', 'VALENCIA', '21');
INSERT INTO `estudiantes` VALUES ('EP201A04', 'ANGEL DANIEL', 'LOPEZ', 'VAZQUEZ', '21');
INSERT INTO `estudiantes` VALUES ('EP201A05', 'MARCO ANTONIO', 'RAMIREZ', 'VALENCIA', '21');
INSERT INTO `estudiantes` VALUES ('EP301A01', 'LUIS ENRIQUE', 'MARTINEZ ', 'FLORES', '13');
INSERT INTO `estudiantes` VALUES ('EP301A02', 'MARIA DE JESUS', 'HERNANDEZ ', 'MEDINA', '13');
INSERT INTO `estudiantes` VALUES ('EP301A03', 'JOSE EDUARDO', 'LINAS', 'GARCIA', '13');
INSERT INTO `estudiantes` VALUES ('EP301A04', 'OLGA LIDIA', 'RUIZ', 'FLORES', '13');
INSERT INTO `estudiantes` VALUES ('EP301A05', 'VALERIA', 'GOMEZ', 'OSORIO', '13');
INSERT INTO `estudiantes` VALUES ('EP201A06', 'YIRE', 'BAIZABAL', 'HERNANDEZ', '21');
INSERT INTO `estudiantes` VALUES ('EP201A07', 'ABEL', 'PAREDES', 'SANCHEZ', '21');
INSERT INTO `estudiantes` VALUES ('EP201A08', 'ALEXIS', 'CONCEPCION', 'LARA', '21');
INSERT INTO `estudiantes` VALUES ('EP201A09', 'ANDREA', 'ROMAN', 'MENDEZ', '21');
INSERT INTO `estudiantes` VALUES ('EP201A10', 'JAIME', 'HERNANDEZ ', 'CABRERA', '21');
INSERT INTO `estudiantes` VALUES ('EP301A06', 'JOSE', 'NOLAZCO', 'HERNANDEZ', '13');
INSERT INTO `estudiantes` VALUES ('EP301A07', 'LUISA ', 'RODRIGUEZ', 'GOMEZ', '13');
INSERT INTO `estudiantes` VALUES ('EP301A08', 'JIMENA', 'DAVILA', 'MEDINA', '13');
INSERT INTO `estudiantes` VALUES ('EP301A09', 'JOSE', 'VALENCIA', 'MACIEL', '13');
INSERT INTO `estudiantes` VALUES ('EP301A10', 'LUZ YESENIA', 'MISH', 'LAGOS', '13');
INSERT INTO `estudiantes` VALUES ('EP401A01', 'ALEJANDRO', 'ABONDADO', 'ACEVEDO', '1');
INSERT INTO `estudiantes` VALUES ('EP401A02', 'CAMILO', 'RODRIGUEZ', 'BOTERO', '1');
INSERT INTO `estudiantes` VALUES ('EP401A03', 'DANIEL', 'GOMEZ', 'DELGADO', '1');
INSERT INTO `estudiantes` VALUES ('EP401A04', 'DANIELA', 'HERNANDEZ', 'BRAVO', '1');
INSERT INTO `estudiantes` VALUES ('EP401A05', 'DIEGO', 'CASIMIRO', 'ZEPEDA', '1');
INSERT INTO `estudiantes` VALUES ('EP401A06', 'EVELIN YANETH', 'MARTINEZ', 'FLORES', '1');
INSERT INTO `estudiantes` VALUES ('EP401A07', 'LAURA', 'DIAZ', 'MEJIA', '1');
INSERT INTO `estudiantes` VALUES ('EP401A08', 'JULIAN', 'ROMEO', 'MONTOYA', '1');
INSERT INTO `estudiantes` VALUES ('EP401A09', 'JORGE', 'MANZO', 'FUENTES', '1');
INSERT INTO `estudiantes` VALUES ('EP401A10', 'MARCELA', 'GARCIA', 'RUEDA', '1');
INSERT INTO `estudiantes` VALUES ('EP501A01', 'MARIA', 'GARCIA', 'MORA', '5');
INSERT INTO `estudiantes` VALUES ('EP501A02', 'PABLO', 'URIBE', 'ANTIA', '5');
INSERT INTO `estudiantes` VALUES ('EP501A03', 'RICARDO', 'VEGA', 'ZAMBRANO', '5');
INSERT INTO `estudiantes` VALUES ('EP501A04', 'SEBASTIAN', 'BORDA', 'MELGUIZO', '5');
INSERT INTO `estudiantes` VALUES ('EP501A05', 'EDUARDO', 'JACOME', 'MEDINA', '5');
INSERT INTO `estudiantes` VALUES ('EP601A01', 'LUZ', 'MARTINEZ', 'MARTINEZ', '9');
INSERT INTO `estudiantes` VALUES ('EP601A02', 'CHISTIAN', 'RODRIGUEZ', 'CHAVA', '9');
INSERT INTO `estudiantes` VALUES ('EP601A03', 'JUAN CAMILO', 'ORTEGA', 'PEÑA', '9');
INSERT INTO `estudiantes` VALUES ('EP601A04', 'JULIANA', 'GAVIRA', 'GARCIA', '9');
INSERT INTO `estudiantes` VALUES ('EP601A05', 'PEDRO', 'ESPINOZA', 'GALMICH', '9');
INSERT INTO `estudiantes` VALUES ('EP601A06', 'EROS ALBERTO', 'RUIZ', 'MARTINEZ', '9');
INSERT INTO `estudiantes` VALUES ('EP701A01', 'CASIMIRO', 'CALDERAZ', 'VELAZQUEZ', '17');
INSERT INTO `estudiantes` VALUES ('EP701A02', 'JOSE', 'MEDINA', 'GOMEZ', '17');
INSERT INTO `estudiantes` VALUES ('EP701A04', 'ALEJANDRA', 'NOVOA', 'JIMENEZ', '17');
INSERT INTO `estudiantes` VALUES ('EP701A05', 'ABEL', 'FLORES', 'HERNANDEZ', '17');
INSERT INTO `estudiantes` VALUES ('EP201B01', 'AMAIRANI ', 'RUIZ', 'ROSAS', '2');
INSERT INTO `estudiantes` VALUES ('EP201B02', 'JUAN', 'CASABLANCAS', 'MARTINEZ', '2');
INSERT INTO `estudiantes` VALUES ('EP201B03', 'LUIS ALBERTO', 'LAGOS', 'PULIDO', '2');
INSERT INTO `estudiantes` VALUES ('EP201B04', 'YADIRA', 'CRUZ', 'CRUZ', '2');
INSERT INTO `estudiantes` VALUES ('EP201B05', 'ADRIANA', 'NOLAZCO', 'SUAREZ', '2');
INSERT INTO `estudiantes` VALUES ('EP301B01', 'JOSE', 'FEREZ', 'SUAREZ', '3');
INSERT INTO `estudiantes` VALUES ('EP301B02', 'MARCO', 'BONDADO', 'GARCIA', '3');
INSERT INTO `estudiantes` VALUES ('EP301B03', 'ARIEL', 'CRUZ', 'CAMACHO', '3');
INSERT INTO `estudiantes` VALUES ('EP301B04', 'CLAUDIA', 'MEDINA', 'MALPICA', '3');
INSERT INTO `estudiantes` VALUES ('EP301B05', 'CARLOS', 'PALENCIA', 'GALLEGOS', '3');
INSERT INTO `estudiantes` VALUES ('EP401B01', 'FERNANDO', 'ROMERO', 'CABRERA', '4');
INSERT INTO `estudiantes` VALUES ('EP401B02', 'ERICK', 'SANCHEZ', 'NIETO', '4');
INSERT INTO `estudiantes` VALUES ('EP401B03', 'LUIS', 'VAZQUEZ', 'ANTONIO', '4');
INSERT INTO `estudiantes` VALUES ('EP401B04', 'JAEL', 'CASTILLEJOS', 'ALEMAN', '4');
INSERT INTO `estudiantes` VALUES ('EP401B05', 'ANDRES', 'CONTRERAS', 'CONTRERAS', '4');
INSERT INTO `estudiantes` VALUES ('EP401B06', 'BRENDA', 'PALMA', 'BIZARRO', '4');
INSERT INTO `estudiantes` VALUES ('EP401B07', 'ISSER', 'SANABRIA', 'LICONA', '4');
INSERT INTO `estudiantes` VALUES ('EP501B01', 'ADRES', 'PINO', 'LUCIO', '6');
INSERT INTO `estudiantes` VALUES ('EP501B02', 'ESTELLA', 'BASALTO', 'RUIZ', '6');
INSERT INTO `estudiantes` VALUES ('EP501B03', 'CAROLINA', 'OCHOA', 'RAMIREZ', '6');
INSERT INTO `estudiantes` VALUES ('EP501B04', 'JUDITH', 'LINAREZ', 'OCHOA', '6');
INSERT INTO `estudiantes` VALUES ('EP601B01', 'MARCO ANTONIO', 'SANCHEZ', 'SANCHEZ', '7');
INSERT INTO `estudiantes` VALUES ('EP601B02', 'HUGO', 'RAMIREZ', 'BLANCO', '7');
INSERT INTO `estudiantes` VALUES ('EP601B03', 'DANEL', 'MIL', 'AQUINO', '7');
INSERT INTO `estudiantes` VALUES ('EP601B04', 'CARMELO', 'PADRON', 'CRUZ', '7');
INSERT INTO `estudiantes` VALUES ('EP601B05', 'LUIS', 'PINO', 'HERRERA', '7');
INSERT INTO `estudiantes` VALUES ('EP801B01', 'BRANDON', 'CABRERA', 'LUCIO', '8');
INSERT INTO `estudiantes` VALUES ('EP801B02', 'MELISA', 'RAMIREZ', 'RAMIREZ', '8');
INSERT INTO `estudiantes` VALUES ('EP801B03', 'ADRIEL', 'HERNANDEZ', 'SANCHEZ', '8');
INSERT INTO `estudiantes` VALUES ('EP801B04', 'LIDIA', 'GALMICH', 'ALVARADO', '8');
INSERT INTO `estudiantes` VALUES ('EP801B05', 'ANTONIO', 'BIZARRO', 'BARAZA', '8');
INSERT INTO `estudiantes` VALUES ('EP901C01', 'ANA LILIANA', 'MONTERO', 'GARCIA', '10');
INSERT INTO `estudiantes` VALUES ('EP901C02', 'CARLOS', 'VASTILLEJOS', 'ZARATE', '10');
INSERT INTO `estudiantes` VALUES ('EP901C03', 'LUIS', 'RIVERA', 'LISBOA', '10');
INSERT INTO `estudiantes` VALUES ('EP901C04', 'JORGE DANIEL', 'CASTILLO', 'QUIVERA', '10');
INSERT INTO `estudiantes` VALUES ('EP100C01', 'JIMENA', 'QUINTERO', 'CHACHA', '11');
INSERT INTO `estudiantes` VALUES ('EP100C02', 'MELINA', 'GARCIA', 'NUÑEZ', '11');
INSERT INTO `estudiantes` VALUES ('EP100C03', 'GERARDO', 'HERNANDEZ', 'MIJANGOS', '11');
INSERT INTO `estudiantes` VALUES ('EP100C04', 'ALBERTO', 'MARTINEZ', 'RITOS', '11');
INSERT INTO `estudiantes` VALUES ('EP300C01', 'Karla ', 'Medina ', 'Aquino ', '12');
INSERT INTO `estudiantes` VALUES ('EP300C02', 'Juan', 'Arias ', 'Romero', '12');
INSERT INTO `estudiantes` VALUES ('EP300C03', 'Juan', 'Antonio ', 'Vázquez ', '12');
INSERT INTO `estudiantes` VALUES ('EP300C04', 'JOSE', 'HERNANDEZ', 'SANCHEZ', '12');
INSERT INTO `estudiantes` VALUES ('EP300C05', 'CARMEN', 'ECHEVERRIA', 'ORTIZ', '12');
INSERT INTO `estudiantes` VALUES ('EP400C01', 'YESENIA', 'ORTIZ', 'SOLIS', '14');
INSERT INTO `estudiantes` VALUES ('EP400C02', 'FREDY', 'SANCHEZ ', 'SOLIS', '14');
INSERT INTO `estudiantes` VALUES ('EP400C03', 'ALBERTO ', 'FARIAS', 'DOMINGUEZ', '14');
INSERT INTO `estudiantes` VALUES ('EP500C01', 'OCTAVIO', 'VELAZQUEZ', 'MONTIEL', '15');
INSERT INTO `estudiantes` VALUES ('EP500C02', 'DANIELA', 'GUAZO', 'SOLIS', '15');
INSERT INTO `estudiantes` VALUES ('EP500C03', 'RODRIGO ', 'ROMANO ', 'CAMACHO', '15');
INSERT INTO `estudiantes` VALUES ('EP500C04', 'JOAQUIN', 'SARMIENTO ', 'CID', '15');
INSERT INTO `estudiantes` VALUES ('EP500C05', 'ANTONIO ', 'VAZQUEZ', 'VAZQUEZ', '15');
INSERT INTO `estudiantes` VALUES ('EP600C01', 'JESUS', 'RODRIGUEZ', 'GOMEZ ', '16');
INSERT INTO `estudiantes` VALUES ('EP600C02', 'LUIS', 'DOMINGUEZ', 'SOLANO', '16');
INSERT INTO `estudiantes` VALUES ('EP600C03', 'MARIA', 'GARCIA ', 'VAZQUEZ ', '16');
INSERT INTO `estudiantes` VALUES ('EP600C04', 'VICTOR', 'RODRIGUEZ', 'SOLANO', '16');

-- ----------------------------
-- Table structure for grupos
-- ----------------------------
DROP TABLE IF EXISTS `grupos`;
CREATE TABLE `grupos` (
  `id_grupo` int(11) NOT NULL,
  `grado` int(11) DEFAULT NULL,
  `letra` char(1) DEFAULT NULL,
  `fk_profesor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_grupo`),
  KEY `fk_profesor` (`fk_profesor`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of grupos
-- ----------------------------
INSERT INTO `grupos` VALUES ('13', '3', 'A', '4');
INSERT INTO `grupos` VALUES ('1', '6', 'A', '1');
INSERT INTO `grupos` VALUES ('5', '5', 'A', '2');
INSERT INTO `grupos` VALUES ('9', '4', 'A', '3');
INSERT INTO `grupos` VALUES ('17', '2', 'A', '5');
INSERT INTO `grupos` VALUES ('21', '1', 'A', '6');
INSERT INTO `grupos` VALUES ('2', '1', 'B', '7');
INSERT INTO `grupos` VALUES ('3', '2', 'B', '8');
INSERT INTO `grupos` VALUES ('4', '3', 'B', '9');
INSERT INTO `grupos` VALUES ('6', '4', 'B', '10');
INSERT INTO `grupos` VALUES ('7', '5', 'B', '11');
INSERT INTO `grupos` VALUES ('8', '6', 'B', '12');
INSERT INTO `grupos` VALUES ('10', '1', 'C', '13');
INSERT INTO `grupos` VALUES ('11', '2', 'C', '14');
INSERT INTO `grupos` VALUES ('12', '3', 'C', '15');
INSERT INTO `grupos` VALUES ('14', '4', 'C', '16');
INSERT INTO `grupos` VALUES ('15', '5', 'C', '17');
INSERT INTO `grupos` VALUES ('16', '6', 'C', '18');

-- ----------------------------
-- Table structure for materias
-- ----------------------------
DROP TABLE IF EXISTS `materias`;
CREATE TABLE `materias` (
  `nrc` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `fk_grupo` int(11) DEFAULT NULL,
  PRIMARY KEY (`nrc`),
  KEY `fk_grupo` (`fk_grupo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of materias
-- ----------------------------
INSERT INTO `materias` VALUES ('2659', 'MATEMATICAS', '21');
INSERT INTO `materias` VALUES ('2660', 'EDUCACION FISICA', '21');
INSERT INTO `materias` VALUES ('2661', 'ESPAÑOL', '21');
INSERT INTO `materias` VALUES ('2262', 'FORMACION CIVICA Y ETICA', '21');
INSERT INTO `materias` VALUES ('2263', 'EDUCACION ARTISTICA', '21');
INSERT INTO `materias` VALUES ('2664', 'EXPLORACION DE LA NATURALEZA Y LA SOCIEDAD', '21');
INSERT INTO `materias` VALUES ('2400', 'FISICA', '13');
INSERT INTO `materias` VALUES ('2401', 'PENSAMIENTO ANALÍTICO', '13');
INSERT INTO `materias` VALUES ('2402', 'HISTORIA', '13');
INSERT INTO `materias` VALUES ('2403', 'GEOGRAFIA', '13');
INSERT INTO `materias` VALUES ('2404', 'MUSICA', '13');
INSERT INTO `materias` VALUES ('2405', 'INGLES', '13');
INSERT INTO `materias` VALUES ('2300', 'ARTES VISUALES', '1');
INSERT INTO `materias` VALUES ('2301', 'GEOGRAFIA AVANZADA', '1');
INSERT INTO `materias` VALUES ('2302', 'GESTION DE PROYECTOS', '1');
INSERT INTO `materias` VALUES ('2303', 'ESTADISTICA', '1');
INSERT INTO `materias` VALUES ('2304', 'ESPAÑOL AVANZADO', '1');
INSERT INTO `materias` VALUES ('2305', 'DANZA', '1');
INSERT INTO `materias` VALUES ('2200', 'INGLES INTERMEDIO', '5');
INSERT INTO `materias` VALUES ('2201', 'HISTORIA DE MEXICO', '5');
INSERT INTO `materias` VALUES ('2202', 'PROGRAMACION', '5');
INSERT INTO `materias` VALUES ('2203', 'ESPAÑOL INTERMEDIO', '5');
INSERT INTO `materias` VALUES ('2204', 'RAZONAMIENTO LOGICO', '5');
INSERT INTO `materias` VALUES ('2205', 'BASE DE DATOS', '5');
INSERT INTO `materias` VALUES ('2500', 'EXPLORACION HISTORICA', '9');
INSERT INTO `materias` VALUES ('2501', 'ANALISIS DE DATOS', '9');
INSERT INTO `materias` VALUES ('2502', 'MATEMATICAS INTERMEDIAS', '9');
INSERT INTO `materias` VALUES ('2503', 'FISICA INTERMEDIA', '9');
INSERT INTO `materias` VALUES ('2504', 'LENGUAS EXTRANJERAS', '9');
INSERT INTO `materias` VALUES ('2505', 'DISEÑO GRAFICO', '9');
INSERT INTO `materias` VALUES ('2700', 'MATEMATICAS BASICAS', '17');
INSERT INTO `materias` VALUES ('2701', 'ESPAÑOL BASICO', '17');
INSERT INTO `materias` VALUES ('2702', 'HISTORIA', '17');
INSERT INTO `materias` VALUES ('2703', 'ARTES', '17');
INSERT INTO `materias` VALUES ('2704', 'INGLES BASICO', '17');
INSERT INTO `materias` VALUES ('2705', 'INTRODUCCION A BASE DE DATOS', '17');
INSERT INTO `materias` VALUES ('2000', 'MATEMATICAS', '2');
INSERT INTO `materias` VALUES ('2001', 'INGLES', '2');
INSERT INTO `materias` VALUES ('2002', 'ESPAÑOL BASICO', '2');
INSERT INTO `materias` VALUES ('2003', 'GEOGRAFIA', '2');
INSERT INTO `materias` VALUES ('2004', 'HISTORIA DE MEXICO', '2');
INSERT INTO `materias` VALUES ('2005', 'LECTURA', '2');
INSERT INTO `materias` VALUES ('2600', 'MATEMATICAS INTERMEDIA', '4');
INSERT INTO `materias` VALUES ('2100', 'ESPAÑOL BASICO', '3');
INSERT INTO `materias` VALUES ('2101', 'RAZONAMIENTO LOGICO', '3');
INSERT INTO `materias` VALUES ('2102', 'HISTORIA ', '3');
INSERT INTO `materias` VALUES ('2103', 'EDUCACION FISICA', '3');
INSERT INTO `materias` VALUES ('2104', 'ARTES', '3');
INSERT INTO `materias` VALUES ('2105', 'REDACCION DE TEXTOS', '3');
INSERT INTO `materias` VALUES ('2601', 'ESPAÑOL INTERMEDIO ', '4');
INSERT INTO `materias` VALUES ('2602', 'HISTORIA INTERMEDIA', '4');
INSERT INTO `materias` VALUES ('2603', 'FISICA', '4');
INSERT INTO `materias` VALUES ('2604', 'ARTES INTERMEDIO', '4');
INSERT INTO `materias` VALUES ('2605', 'INGLES INTERMEDIO ', '4');
INSERT INTO `materias` VALUES ('2800', 'INGLES INTERMEDIO ', '6');
INSERT INTO `materias` VALUES ('2801', 'ESPAÑOL INTERMEDIO ', '6');
INSERT INTO `materias` VALUES ('2802', 'MATEMATICAS INTERMEDIA ', '6');
INSERT INTO `materias` VALUES ('2803', 'GEOGRAFIA ', '6');
INSERT INTO `materias` VALUES ('2804', 'FISICA', '6');
INSERT INTO `materias` VALUES ('2805', 'REDACCION DE TEXTOS', '6');
INSERT INTO `materias` VALUES ('3000', 'ESPAÑOL AVANZADO', '7');
INSERT INTO `materias` VALUES ('3001', 'MATEMATICAS AVANZADA', '7');
INSERT INTO `materias` VALUES ('3002', 'ESTADISTICA', '7');
INSERT INTO `materias` VALUES ('3003', 'HISTORIA DE MEXICO ', '7');
INSERT INTO `materias` VALUES ('3004', 'ARTES VISUALES', '7');
INSERT INTO `materias` VALUES ('3005', 'BASE DE DATOS', '7');
INSERT INTO `materias` VALUES ('3100', 'INGLES AVANZADO ', '8');
INSERT INTO `materias` VALUES ('3101', 'PROGRAMACION', '8');
INSERT INTO `materias` VALUES ('3102', 'BASE DE DATOS ', '8');
INSERT INTO `materias` VALUES ('3103', 'GEOGRAFIA', '8');
INSERT INTO `materias` VALUES ('3104', 'INGLES AVANZADO ', '8');
INSERT INTO `materias` VALUES ('3105', 'COMPRENSION DE TEXTOS ', '8');
INSERT INTO `materias` VALUES ('4000', 'LITERATURA ', '10');
INSERT INTO `materias` VALUES ('4001', 'EDUCACION FISICA', '10');
INSERT INTO `materias` VALUES ('4002', 'DIBUJO ', '10');
INSERT INTO `materias` VALUES ('4003', 'FOTOGRAFIA', '10');
INSERT INTO `materias` VALUES ('4004', 'MATEMATICAS', '10');
INSERT INTO `materias` VALUES ('4005', 'ESPAÑOL ', '10');
INSERT INTO `materias` VALUES ('4100', 'ESPAÑOL', '11');
INSERT INTO `materias` VALUES ('4101', 'MATEMATICAS ', '11');
INSERT INTO `materias` VALUES ('4102', 'ARTES ', '11');
INSERT INTO `materias` VALUES ('4103', 'EDUCACION FISICA ', '11');
INSERT INTO `materias` VALUES ('4104', 'REDACCION DE TEXTOS ', '11');
INSERT INTO `materias` VALUES ('4105', 'INGLES', '11');
INSERT INTO `materias` VALUES ('4200', 'ESPAÑOL INTERMEDIO ', '12');
INSERT INTO `materias` VALUES ('4201', 'MATEMATICAS INTERMEDIA ', '12');
INSERT INTO `materias` VALUES ('4203', 'GEOGRAFIA ', '12');
INSERT INTO `materias` VALUES ('4204', 'MUSICA', '12');
INSERT INTO `materias` VALUES ('4205', 'EDUCACION FISICA ', '12');
INSERT INTO `materias` VALUES ('4300', 'ESPAÑOL INTERMEDIO ', '14');
INSERT INTO `materias` VALUES ('4301', 'MATEMATICAS INTERMEDIA ', '14');
INSERT INTO `materias` VALUES ('4302', 'HISTORIA', '14');
INSERT INTO `materias` VALUES ('4303', 'REDACCION DE TEXTOS ', '14');
INSERT INTO `materias` VALUES ('4304', 'EDUCACION FISICA ', '14');
INSERT INTO `materias` VALUES ('4305', 'VALORES ', '14');
INSERT INTO `materias` VALUES ('4500', 'INGLES AVANZADO ', '15');
INSERT INTO `materias` VALUES ('4501', 'RAZONAMIENTO ANALITICO', '15');
INSERT INTO `materias` VALUES ('4502', 'LENGUAS EXTRANJERAS ', '15');
INSERT INTO `materias` VALUES ('4503', 'HISTORIA ', '15');
INSERT INTO `materias` VALUES ('4504', 'EDUCACION FISICA ', '15');
INSERT INTO `materias` VALUES ('4505', 'FISICA ', '15');
INSERT INTO `materias` VALUES ('4600', 'INGLES AVANZADO ', '16');
INSERT INTO `materias` VALUES ('4601', 'BASE DE DATOS ', '16');
INSERT INTO `materias` VALUES ('4602', 'ESPAÑOL AVANZADO ', '16');
INSERT INTO `materias` VALUES ('4603', 'ARTES VISUALES ', '16');
INSERT INTO `materias` VALUES ('4604', 'GEOGRAFIA DE MEXICO ', '16');
INSERT INTO `materias` VALUES ('4605', 'HISTORIA DE MEXICO ', '16');

-- ----------------------------
-- Table structure for profesores
-- ----------------------------
DROP TABLE IF EXISTS `profesores`;
CREATE TABLE `profesores` (
  `numero_personal` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido_paterno` varchar(50) DEFAULT NULL,
  `apellido_materno` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`numero_personal`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of profesores
-- ----------------------------
INSERT INTO `profesores` VALUES ('1', 'JUAN', 'PEREZ', 'JIMENEZ');
INSERT INTO `profesores` VALUES ('2', 'MARIA', 'MARTINEZ', 'GONZALEZ');
INSERT INTO `profesores` VALUES ('3', 'JUANA ', 'HERNANDEZ', 'MARTINEZ');
INSERT INTO `profesores` VALUES ('4', 'SOFIA', 'GARCIA', 'LOPEZ');
INSERT INTO `profesores` VALUES ('5', 'DULCE', 'RAMIREZ', 'SANCHEZ');
INSERT INTO `profesores` VALUES ('6', 'BLANCA', 'CRUZ', 'GOMEZ');
INSERT INTO `profesores` VALUES ('7', 'DANIELA DE JESUS', 'JUAREZ', 'DIAZ');
INSERT INTO `profesores` VALUES ('8', 'JOSE ', 'ALVARADO', 'RODRIGUEZ');
INSERT INTO `profesores` VALUES ('9', 'XOCHILT', 'MARTINEZ', 'MARTINEZ');
INSERT INTO `profesores` VALUES ('10', 'JULIA DEL CARMEN', 'CABRERA', 'HERNANDEZ');
INSERT INTO `profesores` VALUES ('11', 'ISIS VALERIA', 'LOPEZ', 'VAZQUEZ');
INSERT INTO `profesores` VALUES ('12', 'JOSE CARLOS', 'MEDINA', 'ZARATE');
INSERT INTO `profesores` VALUES ('13', 'MARIA DEL CARMEN', 'CAMBRANIS', 'ABRAHAM');
INSERT INTO `profesores` VALUES ('14', 'EDUARDO', 'SALINAS', 'CABRERA');
INSERT INTO `profesores` VALUES ('15', 'LUIS ERNESTO', 'GOMEZ', 'HERNANDEZ');
INSERT INTO `profesores` VALUES ('16', 'NESTOR', 'VELAZQUEZ', 'RUIZ');
INSERT INTO `profesores` VALUES ('17', 'JUANA ', 'ROMAN', 'ZORATI');
INSERT INTO `profesores` VALUES ('18', 'NATAEL', 'CANO', 'MEDINA');
INSERT INTO `profesores` VALUES ('19', 'OLGA', 'HERNANDEZ', 'RUIZ');
INSERT INTO `profesores` VALUES ('20', 'VALERIA', 'CASTRO', 'HERRERA');
