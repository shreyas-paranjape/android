package com.goaamigo.traveller.module.product.view.fragment;

public class ProductMapFragment {
        //extends MapFragment {

    /*private static final String TAG = GoaMapActivity.class.getName();
    public static final LatLong LATLONG_PANAJI = new LatLong(15.4989, 73.8278);

    private ProductAdapter productAdapter;

    public ProductMapFragment(){
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productAdapter = (ProductAdapter) getArguments()
                .getSerializable("PRODUCT_ADAPTER");
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_map;
    }

    @Override
    protected int getMapViewId() {
        return R.id.mapView;
    }

    @Override
    protected String getMapFileName() {
        return "maps/goa.map";
    }


    @Override
    protected MapPosition getDefaultInitialPosition() {
        return new MapPosition(LATLONG_PANAJI, (byte) 12);
    }

    public void onEvent(ProductAdapter.ProductDataSetChanged event) {
        //refreshLayers();
    }



    private void addBalloonForProduct(Product product) {
        Util.addBalloonMarker(
                getActivity(),
                mapView,
                LATLONG_PANAJI,
                product.getName(),
                R.mipmap.balloon_overlay_unfocused);
    }
*/
}
