package at.wirecube.examples.products.application.view;

public interface View {
  interface ProductView {

    interface POST{}
    interface PUT extends POST{ }
  }
}
