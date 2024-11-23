package id.tirzasrwn.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import id.tirzasrwn.cupcake.R
import id.tirzasrwn.cupcake.ui.components.FormattedPriceLabel
import id.tirzasrwn.cupcake.ui.theme.CupcakeTheme

/**
 * Composable that displays the list of items as [RadioButton] options, [onSelectionChanged] lambda
 * that notifies the parent composable when a new value is selected, [onCancelButtonClicked] lambda
 * that cancels the order when user clicks cancel and [onNextButtonClicked] lambda that triggers the
 * navigation to next screen
 */
@Composable
fun SelectOptionScreen(
    subtotal: String,
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}, // notify parent about selection change
    onCancelButtonClicked: () -> Unit = {}, // handle cancel action
    onNextButtonClicked: () -> Unit = {}, // handle next action
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") } // remember the selected option across recompositions

    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceBetween) {
        // Options list with selectable radio buttons
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            options.forEach { item ->
                Row(
                    modifier =
                    Modifier.selectable(
                        selected = selectedValue == item, // mark as selected
                        onClick = {
                            selectedValue = item // update selected value
                            onSelectionChanged(item) // notify parent
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item, // indicate selected state
                        onClick = {
                            selectedValue = item // update selection
                            onSelectionChanged(item) // notify parent
                        }
                    )
                    Text(item) // display option text
                }
            }

            // divider for visual separation
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )

            // display subtotal
            FormattedPriceLabel(
                subtotal = subtotal,
                modifier =
                Modifier.align(Alignment.End)
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            )
        }

        // action buttons (Cancel and Next)
        Row(
            modifier =
            Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement =
            Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ) {
            // cancel button
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text(stringResource(R.string.cancel))
            }

            // next button, enabled only if an option is selected
            Button(
                modifier = Modifier.weight(1f),
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) { Text(stringResource(R.string.next)) }
        }
    }
}

// preview
@Preview
@Composable
fun SelectOptionPreview() {
    CupcakeTheme {
        SelectOptionScreen(
            subtotal = "299.99",
            options = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
            modifier = Modifier.fillMaxHeight()
        )
    }
}
