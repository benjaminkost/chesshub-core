-- Drop the status field/column
ALTER TABLE `request`
DROP COLUMN `status`;

-- Add the accepted field/column
ALTER TABLE `request`
ADD `accepted` BOOLEAN;


