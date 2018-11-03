package ru.unn.agile.vector3d.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.unn.agile.vector3d.model.Vector3D;
import ru.unn.agile.vector3d.viewmodel.validators.FieldValidator;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final static String EMPTY_STRING = "";

    private final StringProperty vectorX = new SimpleStringProperty();
    private final StringProperty vectorY = new SimpleStringProperty();
    private final StringProperty vectorZ = new SimpleStringProperty();

    private final StringProperty otherVectorX = new SimpleStringProperty();
    private final StringProperty otherVectorY = new SimpleStringProperty();
    private final StringProperty otherVectorZ = new SimpleStringProperty();

    private final StringProperty multiplicationCoeff = new SimpleStringProperty();

    private final ObjectProperty<ObservableList<Vector3D.Operation>> operations =
            new SimpleObjectProperty<>(FXCollections.observableArrayList(Vector3D.Operation.values()));
    private final ObjectProperty<Vector3D.Operation> operation = new SimpleObjectProperty<>();

    private final BooleanProperty calculationDisabled = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public final ObservableList<Vector3D.Operation> getOperations() {
        return operations.get();
    }

    public StringProperty vectorXProperty() {
        return vectorX;
    }

    public StringProperty vectorYProperty() {
        return vectorY;
    }

    public StringProperty vectorZProperty() {
        return vectorZ;
    }

    public StringProperty otherVectorXProperty() {
        return otherVectorX;
    }

    public StringProperty otherVectorYProperty() {
        return otherVectorY;
    }

    public StringProperty otherVectorZProperty() {
        return otherVectorZ;
    }

    public StringProperty multiplicationCoeffProperty() {
        return multiplicationCoeff;
    }

    public ObjectProperty<Vector3D.Operation> operationProperty() {
        return operation;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public boolean isCalculationDisabled() {
        return calculationDisabled.get();
    }

    public BooleanProperty calculationDisabledProperty() {
        return calculationDisabled;
    }

    public ViewModel() {
        setDefaultVector();
        setDefaultOtherVector();
        setDefaultMultCoeffAndResult();
        setDefaultOperationAndStatus();

        initCalculateStateListener();
        initFieldsListeners();
    }

    public void calculate() {

    }

    private void initFieldsListeners() {
        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(vectorX);
                add(vectorY);
                add(vectorZ);
                add(otherVectorX);
                add(otherVectorY);
                add(otherVectorZ);
            }
        };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    private void initCalculateStateListener() {
        BooleanBinding couldCalculate = new BooleanBinding() {
            {
                super.bind(vectorX, vectorY, vectorZ, otherVectorX,
                        otherVectorY, otherVectorZ, multiplicationCoeff);
            }

            @Override
            protected boolean computeValue() {
                return getInputStatus() == Status.READY;
            }
        };
        calculationDisabled.bind(couldCalculate.not());
    }

    private Status getInputStatus() {
        Status inputStatus = Status.READY;

        Vector3D.Operation operation = operationProperty().get();

        String vectorXValue = vectorX.get();
        String vectorYValue = vectorY.get();
        String vectorZValue = vectorZ.get();

        String otherXValue = otherVectorX.get();
        String otherYValue = otherVectorY.get();
        String otherZValue = otherVectorZ.get();

        FieldValidator fieldValidator = new FieldValidator();

        if (Vector3D.Operation.ADD.equals(operation)) {
            inputStatus = fieldValidator.validateVector(vectorXValue, vectorYValue, vectorZValue);
            if (Status.READY.equals(inputStatus)) {
                inputStatus = fieldValidator.validateVector(otherXValue, otherYValue, otherZValue);
            }
        }

        return inputStatus;
    }

    private void setDefaultVector() {
        vectorX.set(EMPTY_STRING);
        vectorY.set(EMPTY_STRING);
        vectorZ.set(EMPTY_STRING);
    }

    private void setDefaultOtherVector() {
        otherVectorX.set(EMPTY_STRING);
        otherVectorY.set(EMPTY_STRING);
        otherVectorZ.set(EMPTY_STRING);
    }

    private void setDefaultMultCoeffAndResult() {
        multiplicationCoeff.set(EMPTY_STRING);
        result.set(EMPTY_STRING);
    }

    private void setDefaultOperationAndStatus() {
        operation.set(Vector3D.Operation.ADD);
        status.set(Status.WAITING.toString());
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getInputStatus().toString());
        }
    }

    public enum Status {
        WAITING("Please provide input data"),
        READY("Press 'Calculate' or Enter"),
        BAD_FORMAT("Bad format"),
        SUCCESS("Success");

        private final String name;

        Status(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
