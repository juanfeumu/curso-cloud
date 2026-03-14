package es.um.atica.umufly.paquetes.domain.model;


public enum TarifaEnvio {

	FRAGIL( 2.5d ), NORMAL( 4d );

	private double importeKilo;

	TarifaEnvio( double valor ) {
		this.importeKilo = valor;
	}

	public double getImporteKilo() {
		return importeKilo;
	}

	public static TarifaEnvio ofIsFragil( boolean esFragil ) {
		if ( esFragil ) {
			return TarifaEnvio.FRAGIL;
		}
		return TarifaEnvio.NORMAL;
	}
}
