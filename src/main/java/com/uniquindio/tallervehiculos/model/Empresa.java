    package com.uniquindio.tallervehiculos.model;

    import java.time.LocalDate;
    import java.util.ArrayList;

    public class Empresa {

        private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        private ArrayList<Auto> autos = new ArrayList<>();
        private ArrayList<Moto> motos = new ArrayList<>();
        private ArrayList<Camioneta> camionetas = new ArrayList<>();
        private ArrayList<Cliente> clientes = new ArrayList<>();
        private ArrayList<Reserva> reservas = new ArrayList();

        // Precio por dia
        private final static double precio = 5.2;

        // Constructor
        // Constructor Vacio
        public Empresa() {
            quemarClientes();
        }

        // Getters y Setters
        public ArrayList<Vehiculo> getVehiculos() {
            return vehiculos;
        }

        public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
            this.vehiculos = vehiculos;
        }

        public ArrayList<Auto> getAutos() {
            return autos;
        }

        public void setAutos(ArrayList<Auto> autos) {
            this.autos = autos;
        }

        public ArrayList<Moto> getMotos() {
            return motos;
        }

        public void setMotos(ArrayList<Moto> motos) {
            this.motos = motos;
        }

        public ArrayList<Camioneta> getCamionetas() {
            return camionetas;
        }

        public void setCamionetas(ArrayList<Camioneta> camionetas) {
            this.camionetas = camionetas;
        }

        public ArrayList<Cliente> getClientes() {
            return clientes;
        }

        public void setClientes(ArrayList<Cliente> clientes) {
            this.clientes = clientes;
        }

        public ArrayList<Reserva> getReservas() {
            return reservas;
        }

        public void setReservas(ArrayList<Reserva> reservas) {
            this.reservas = reservas;
        }

    //Metodos CRUD
    public int addVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            if (vehiculo instanceof Auto) {
                Auto auto = (Auto) vehiculo;
                autos.add(auto);
                vehiculos.add(auto);
                return 1;
            } else if (vehiculo instanceof Moto) {
                Moto moto = (Moto) vehiculo;
                motos.add(moto);
                vehiculos.add(moto);
                return 2;
            } else if (vehiculo instanceof Camioneta) {
                Camioneta camioneta = (Camioneta) vehiculo;
                camionetas.add(camioneta);
                vehiculos.add(camioneta);
                return 3;
            } else {
                return 0;
            }
        }
        return 0;
    }



        public int RemoveVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            if (vehiculo instanceof Auto auto){
                autos.remove(auto);
                return 1;
            }else if (vehiculo instanceof Moto moto){
                motos.remove(moto);
                return 2;
            } else if (vehiculo instanceof Camioneta camioneta) {
                camionetas.remove(camioneta);
                return 3;
            }else{
                return 0;
            }
        }
        return 0;
    }

    public int updateVehiculo(Vehiculo vehiculo,String placa) {
        if (vehiculo != null) {
            if (vehiculo instanceof Auto){
                Auto autoViejo = autos.stream().filter(a -> a.getPlaca().equals(placa)).findFirst().get();
                autos.set(autos.indexOf(autoViejo), (Auto)vehiculo);
                return 1;
            }else if (vehiculo instanceof Moto){
                Moto motoVieja = motos.stream().filter(m -> m.getPlaca().equals(placa)).findFirst().get();
                motos.set(motos.indexOf(motoVieja), (Moto)vehiculo);
                return 2;
            } else if (vehiculo instanceof Camioneta) {
                Camioneta camionetaVieja = camionetas.stream().filter(c -> c.getPlaca().equals(placa)).findFirst().get();
                camionetas.set(camionetas.indexOf(camionetaVieja), (Camioneta)vehiculo);
                return 3;

            }else {
                return 0;
            }
        }else{
            return 0;
        }
    }

    public int addCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.add(cliente);
            System.out.println("si");
            return 1;
        }
        return 0;
    }

    public boolean autenticarCliente(String password) {
        return clientes.stream().anyMatch(c -> c.getPassword().equals(password));
    }

    public int removeCliente(Cliente cliente) {
        if (cliente != null) {
            clientes.remove(cliente);
            return 1;
        }
        return 0;
    }

    public int updateCliente(Cliente cliente, String password) {
        if (cliente != null) {
            Cliente clienteViejo = clientes.stream().filter(p -> p.getPassword().equals(password)).findFirst().get();
            clientes.set(clientes.indexOf(clienteViejo), cliente);
            return 1;
        }
        return 0;
    }

    public int addReserva(Reserva reserva) {
        if (reserva != null) {
            Vehiculo vehiculo = encontrarVehiculo(reserva.getPlacaVehiculo());
            reserva = calcularCostoReserva(vehiculo,reserva.getCliente(),reserva.getCantidadDias());
            reservas.add(reserva);
            Cliente cliente = reserva.getCliente();
            cliente.getReservas().add(reserva);
            return 1;
        }
        return 0;
    }

    public Vehiculo encontrarVehiculo(String placa) {
        if (placa != null) {
            Vehiculo vehiculo = encontrarAuto(placa);
            if (vehiculo != null) {
                return vehiculo;
            }

            vehiculo = encontrarMoto(placa);
            if (vehiculo != null) {
                return vehiculo;
            }

            vehiculo = encontrarCamioneta(placa);
            if (vehiculo != null) {
                return vehiculo;
            }

            vehiculo = encontrar(placa);
            if (vehiculo != null) {
                return vehiculo;
            }
        }
        return null;
    }



    public Vehiculo encontrar(String placa){
            return vehiculos.stream().filter(m -> m.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    public Moto encontrarMoto(String placa) {
            return motos.stream().filter(m -> m.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    public Camioneta encontrarCamioneta(String placa) {
            return camionetas.stream().filter(c -> c.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    public Auto encontrarAuto(String placa) {
            return autos.stream().filter(a -> a.getPlaca().equals(placa)).findFirst().orElse(null);
    }


        public int removeReserva(Reserva reserva) {
        if (reserva != null) {
            reservas.remove(reserva);
            return 1;
        }
        return 0;
    }

    public int updateReserva(Reserva reserva, String placa) {
        if (reserva != null) {
            Reserva reservaVieja = reservas.stream().filter(r -> r.getPlacaVehiculo().equals(placa)).findFirst().get();
            reservas.set(reservas.indexOf(reservaVieja), reserva);
            return 1;
        }
        return 0;
    }

    //Metodo para calcular el costo de la reserva
        public Reserva calcularCostoReserva(Vehiculo vehiculo, Cliente cliente, int dias) {
            if (vehiculo == null) {
                throw new IllegalArgumentException("El vehículo no puede ser null.");
            }

            Reserva reserva = new Reserva();
            reserva.setCliente(cliente);
            reserva.setCantidadDias(dias);
            reserva.setFechaInicio(LocalDate.now());
            reserva.setPlacaVehiculo(vehiculo.getPlaca());


            if (vehiculo instanceof Auto) {
                reserva.setTipoVehiculo("AUTO");
                reserva.setPrecio(precio * dias);

            } else if (vehiculo instanceof Moto) {
                reserva.setTipoVehiculo("MOTO");
                // Verifica si la moto es Automática
                if (((Moto) vehiculo).cajaCambios.equals(CajaCambios.AUTOMATICA)) {
                    reserva.setPrecio(precio + 3.0);
                } else {
                    reserva.setPrecio(precio);
                }
                // Verifica si es una camioneta
            } else if (vehiculo instanceof Camioneta) {
                reserva.setTipoVehiculo("CAMIONETA");
                reserva.setPrecio(precio + (((Camioneta) vehiculo).getCapacidadCarga() * 0.5));
            }
            return reserva;
        }


        //Metodo para quemar clientes
        public void quemarClientes(){
            Cliente cliente = new Cliente("90","90");
            Auto auto = new Auto("90","90",null,null,2);
            clientes.add(cliente);
            autos.add(auto);
    }

}
