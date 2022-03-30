package br.com.gabriel.analyzer.events;

public sealed interface Event permits ProponentEvent, ProposalEvent, WarrantyEvent {
}
